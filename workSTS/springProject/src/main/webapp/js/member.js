// 로그인 버튼
$("#loginBtn").click(function(){
	$("#loginIdDiv").empty();
	$("#loginPwdDiv").empty();
	
	if($("#loginId").val()=="") {
		$("#loginIdDiv").text("아이디를 입력하세요").css("color", "tomato").css("font-size","8pt");
	} else if($("#loginPwd").val()=="") {
		$("#loginPwdDiv").text("비밀번호을 입력하세요").css("color", "tomato").css("font-size","8pt");
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/member/login",
			data : "id="+$("#loginId").val()+"&pwd="+$("#loginPwd").val(),
			dataType : "text",
			success : function(data){
				if(data == "success") {
					location.href="/springProject/main/index";
				} else if(data == "fail") {
					$("#loginResultDiv").text("로그인 실패").css("color", "tomato").css("font-size","15pt").css("font-weight", "bold");
				}
			},
			error : function(e){
				console.log(e);
				alert("실패");
			}
		});
	}
});

// 로그아웃 버튼
$("#logoutBtn").click(function(){
	$.ajax({
		type: "post",
		url: "/springProject/member/logout",
		success: function(){
			location.href="/springProject/main/index";
		},
		error : function(e){
			console.log(e);
			alert("실패");
		}
	});
});

// 회원가입 버튼
$("#writeBtn").click(function(){
	$("#writeForm div").empty();
	
	if($("#name").val() == "") {
		$("#nameDiv").text("이름을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#name").focus();
	} else if($("#id").val() == "") {
		$("#idDiv").text("아이디를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#id").focus();
	} else if($("#pwd").val() == "") {
		$("#pwdDiv").text("비밀번호를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#pwd").focus();
	} else if($("#repwd").val() == "") {
		$("#repwdDiv").text("비밀번호를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#repwd").focus();
	} else if($("#pwd").val() != $("#repwd").val()) {
		$("#repwdDiv").text("비밀번호가 맞지 않습니다").css("color", "tomato").css("font-size","8pt");
		$("#repwd").focus();
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/member/write",
			data : $("#writeForm").serialize(),
			success : function(){
				alert(
						"회원가입 성공!");
				location.href="/springProject/main/index";
			},
			error : function(e){
				console.log(e);
				alert("실패");
			}
		});
	}
});

$("#id").focusout(function(){
	if($("#id").val() == "") {
		$("#idDiv").text("먼저 아이디를 입력하세요").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
		$("#id").focus();
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/member/checkId",
			data : {"id" : $("#id").val()},
			dataType : "text",
			success : function(data){
				if(data == "exist") {
					$("#idDiv").text("아이디 사용 불가능").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
					$("#id").focus();
				} else if (data == "not_exist") {
					$("#idDiv").text("아이디 사용 가능").css("color", "blue").css("font-size", "8pt").css("font-weight", "bold");
				}
			},
			error : function(e){
				console.log(e);
				alert("실패");
			}
		});
	}
});

$("#resetBtn").click(function(){
	$("#writeForm div").empty();
});

// 회원가입 창에서 주소 검색
$("#postBtn").click(function(){
	window.open("/springProject/member/checkPost","","width=500 height=500 left=500 top=250 scrollbars=yes");
});

$("#postSearchBtn").click(function(){
	$.ajax({
		type : "post",
		url : "/springProject/member/postList",
		data :  {"sido" : $("#sido option:selected").val(), "sigungu" : $("#sigungu").val(), "roadname" : $("#roadname").val()},
		dataType : "json",
		success : function(data) {
			//alert(JSON.stringify(data));
			$.each(data.list, function(index, items){
				if(items.ri == null) items.ri = "";
				if(items.buildingname == null) items.buildingname = "";
				var address = items.sido +" "+ items.sigungu +" "+ items.yubmyundong +" "+ items.ri +" "+ items.roadname +" "+ items.buildingname
				$("<tr/>").append($("<td/>",{
					align : "center",
					text : items.zipcode
				})).append($("<td/>",{
					colspan : 3,
					align : "left"
				}).append($("<a/>",{
					id: "addressA",
					href : "#",
					text : address,
				}))).appendTo("#postTable");

			});
		},
		error : function(e){
			console.log(e);
			alert("실패");
		}
	});
});

// 주소 검색 후 창닫기
$("#postTable").on("click", "a" ,function(){
	var aBtn = $(this);
	var tr = aBtn.parent().parent();
	var td = tr.children();
	
	opener.document.getElementById("daum_zipcode").value = td.eq(0).text();
	opener.document.getElementById("daum_addr1").value = td.eq(1).text();
	window.close();
	opener.document.getElementById("daum_addr2").focus();
});

// 회원정보수정 할 때 유효성 검사
function checkModify() {
	if(document.modifyForm.name.value == "") {
		alert("이름을 입력하세요");
		document.modifyForm.name.focus();
	} else if(document.modifyForm.pwd.value == "") {
		alert("비밀번호를 입력하세요");
		document.modifyForm.pwd.focus();
	} else if(document.modifyForm.repwd.value == "") {
		alert("비밀번호를 입력하세요");
		document.modifyForm.repwd.focus();
	} else if (document.modifyForm.pwd.value != document.modifyForm.repwd.value) {
		alert("비밀번호가 맞지 않습니다");
	} else {
		document.modifyForm.submit();
	}
}
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
		type: "get",
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
				alert("회원가입 성공!");
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

// 우편번호 찾기
$("#postSearchBtn").click(function(){
	$("#sidoDiv").empty();
	$("#sigunguDiv").empty();
	$("#roadnameDiv").empty();

	if($("#sido").val()=="시도선택") 
		$("#sidoDiv").text("시도를 선택하세요").css("color", "red").css("font-size","8pt");
	else if($("#sido").val()!="세종" && $("#sigungu").val()=="")
		$("#sigunguDiv").text("시군구를 입력하세요").css("color", "red").css("font-size","8pt");
	else if($("#roadname").val()=="")
		$("#roadnameDiv").text("도로명을 입력하세요").css("color", "red").css("font-size","8pt");
	else {
		$.ajax({
			type : "post",
			url : "/springProject/member/postList",
			data :  $("#postSearchForm").serialize(),
			dataType : "json",
			success : function(data){
				//alert(JSON.stringify(data));
				$("#postTable tr:gt(2)").remove();
				
				$.each(data.list, function(index, items){
					var address = items.sido +" "+ items.sigungu +" "+ items.yubmyundong +" "+ items.ri +" "+ items.roadname +" "+ items.buildingname;
					
					address = address.replace(/null/g, "");	// g는 정규표현식. 발생하는 모든 pattern에 대한 전역 검색
					
					$("<tr/>").append($("<td/>",{
						align : "center",
						text : items.zipcode
					})).append($("<td/>",{
						colspan : 3,
						align : "left"
						}).append($("<a/>",{
							id : "addressA",
							href : "#",
							text : address,
						}))
					).appendTo($("#postTable"));
				}); //each
				
				// 주소 검색후 창닫기
				$("a").click(function(){
					//alert($(this).prop("tagName"));
					//alert($(this).text());	// 주소
					//alert($(this).parent().prev().text()); // 우편번호
					
					$("#daum_zipcode", opener.document).val($(this).parent().prev().text());
					$("#daum_addr1", opener.document).val($(this).text());
					window.close();
					$("#daum_addr2", opener.document).focus();
				});
			},
			error : function(e){
				console.log(e);
				alert("실패");
			}
		});
	}
});

// 회원정보수정
$("#modifyBtn").click(function(){
	$("#modifyBtn div").empty();
	
	if($("#name").val() == "") {
		$("#nameDiv").text("이름을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#name").focus();
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
			url : "/springProject/member/modify",
			data :  $("#modifyForm").serialize(),
			success : function(){
				alert("회원정보 수정완료!");
				location.href="/springProject/member/logout";
			},
			error : function(e){
				console.log(e);
				alert("실패");
			}
		});
	}
});
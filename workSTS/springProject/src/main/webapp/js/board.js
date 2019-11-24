// 게시글 삭제
function checkBoardDelete(seq) {
	if(confirm("정말 삭제하시겠습니까?") == true) {
		location.href="/springProject/board/boardDelete?seq="+seq;
	} else
		return false;
}
// 글쓰기
$("#boardWriteBtn").click(function(){
	$("#subjectDiv").empty();
	$("#contentDiv").empty();
	
	if($("#subject").val() == "") {
		$("#subjectDiv").text("제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#subject").focus();
	} else if($("#content").val() == "") {
		$("#contentDiv").text("내용을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#content").focus();
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/board/boardWrite",
			data : {"subject" : $("#subject").val(), "content" : $("#content").val()},
			success : function(){
				alert("글쓰기 성공");
				location.href="/springProject/board/boardList";
			}, 
			error : function(err) {
				console.log(err);
			}
		});
	}
});

// 게시글 목록
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "/springProject/board/getBoardList",
		data : {"pg" : $("#pg").val()},
		dataType : "json",
		success : function(data){
			//alert(JSON.stringify(data));
			
			$.each(data.boardList, function(index, items){
				$("<tr/>").append($("<td/>", {
					align : "center",
					text : items.seq
				})).append($("<td/>",{
					
				}).append($("<a/>", {
					href : "javascript:void(0)",
					id : "subjectA",
					class: items.seq+"",
					text : items.subject
				}))).append($("<td/>", {
					align : "center",
					text : items.id
				})).append($("<td/>", {
					align : "center",
					text : items.logtime
				})).append($("<td/>", {
					align : "center",
					text : items.hit
				})).appendTo($("#boardListTable"));
				
				// 답글 				
				for(i = 0; i<items.lev; i++) {
					$("."+items.seq).before("&emsp;");	// 내 태그의 앞부분
				}// for
				if(items.pseq != 0) {
					$("."+items.seq).before($("<img/>",{
						src: "../image/reply.gif"
					}));
				}
			});
			
			// 페이징처리
			$("#boardPagingDiv").html(data.boardPaging.pagingHTML);
			
			// 로그인 여부
			$("#boardListTable").on("click", "a", function(){
				
					//alert($(this).parent().prev().text());
					//alert($(this).attr("class"));
					location.href="/springProject/board/boardView?seq="+$(this).attr("class")+"&pg="+$("#pg").val();
				
			});
		},
		error : function(e){
			console.log(e);
			alert("실패");
		}
	});
});

// 검색
$("#searchBtn").click(function(event, str){
	$("#searchDiv").empty();
	
	if(str != "trigger") $("input[name=pg]").val(1);
	
	if($("#condition").val() == "") {
		$("#searchDiv").text("검색어를 입력하세요").css("color", "tomato");
		$("#condition").focus();
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/board/boardSearch",
			data : $("#searchFrom").serialize(), //serialize()은 name 속성을 가지고감.
			dataType : "json",
			success : function(data) {
				//alert(data.boardSearchList);
				$("#boardListTable tr:gt(0)").remove();
				$.each(data.list, function(index, items){
					$("<tr/>").append($("<td/>", {
						align : "center",
						text : items.seq
					})).append($("<td/>",{
						
					}).append($("<a/>", {
						href : "javascript:void(0)",
						text : items.subject,
						id : "subjectA",
						class: items.seq+""
						
					}))).append($("<td/>", {
						align : "center",
						text : items.id
					})).append($("<td/>", {
						align : "center",
						text : items.logtime
					})).append($("<td/>", {
						align : "center",
						text : items.hit
					})).appendTo($("#boardListTable"));
				
					// 답글 				
					for(i = 0; i<items.lev; i++) {
						$("."+items.seq).before("&emsp;");	// 내 태그의 앞부분
					}// for
					if(items.pseq != 0) {
						$("."+items.seq).before($("<img/>",{
							src: "../image/reply.gif"
						}));
					}
				}); // each
				// 페이징처리
				$("#boardPagingDiv").html(data.boardPaging.pagingHTML);
			},
			error : function(e){
				console.log(e);
				alert("실패");
			}
		});
	}
});

// 답글
$("#replyBtn").click(function(){
	$("#subjectDiv").empty();
	$("#contentDiv").empty();
	
	if($("#subject").val() == "") {
		$("#subjectDiv").text("제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#subject").focus();
	} else if($("#content").val() == "") {
		$("#contentDiv").text("내용을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#content").focus();
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/board/boardReply",
			data : $("#boardReplyForm").serialize(),
			success : function(){
				alert("답글쓰기 성공");
				location.href="/springProject/board/boardList?pg="+$("input[name=pg]").val();
			}, 
			error : function(err) {
				console.log(err);
			}
		});
	}
});

// 수정
$("#modifyBtn").click(function(){
	$("#subjectDiv").empty();
	$("#contentDiv").empty();
	
	if($("#subject").val() == "") {
		$("#subjectDiv").text("제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#subject").focus();
	} else if($("#content").val() == "") {
		$("#contentDiv").text("내용을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#content").focus();
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/board/boardModify",
			data : $("#boardModifyForm").serialize(),
			success : function(){
				alert("글수정 성공");
				location.href="/springProject/board/boardList?pg="+$("input[name=pg]").val();
			}, 
			error : function(err) {
				console.log(err);
			}
		});
	}
});
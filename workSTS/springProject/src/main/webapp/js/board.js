// 게시글 삭제
function checkBoardDelete(seq) {
	if(confirm("정말 삭제하시겠습니까?") == true) {
		location.href="/miniproject/board/boardDelete.do?seq="+seq;
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
			}, 
			error : function(err) {
				console.log(err);
			}
		});
	}
});

$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "/springProject/board/getBoardList",
		data : {"pg" : $("#pg").val() },
		dataType : "json",
		success : function(data){
			//alert(JSON.stringify(data));
			
			$.each(data.boardList, function(index, items){
				$("<tr/>").append($("<td/>", {
					align : "center",
					text : items.seq
				})).append($("<td/>",{
					
				}).append($("<a/>", {
					href : "#",
					id : "subjectA",
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
				})).appendTo("#boardTable");
			});
		},
		error : function(e){
			console.log(e);
			alert("실패");
		}
	});
});

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

<h2>글쓰기</h2>
<!-- <form>
 <table border="1" cellspacing="0" cellpadding="5">
 	<tr>
 		<td width="80" align="center">제목</td>
 		<td width="200">
 			<input type="text" id="subject" name="subject" style="width: 250px;">
 			<div id="subjectDiv"></div>
 		</td>
 	</tr>
 	<tr>
 		<td width="80" align="center">내용</td>
 		<td>
 		 <textarea id="content" name="content" cols="40" rows="10" style="resize: none"></textarea>
 		 <div id="contentDiv"></div>
 		</td> 
 	</tr>
 	<tr>
 		<td colspan="2" align="center">
 			<input type="button" id="boardWriteBtn" value="글쓰기">
 			<input type="reset" value="다시작성">
 		</td>
 	</tr>
 </table>
</form> -->

<form id="summerForm" method="post" action="/summernotewrite">
	<input type="text" name="subject" id="subject" placeholder="제목">
	<div id="subjectDiv"></div>
	<br>
	<textarea id="summernote" name="content"></textarea>
	<div id="contentDiv"></div>
	<input type="button" id="subBtn" value="글작성">
</form>
<script>
$(document).ready(function(){
	$("#summernote").summernote({
		placeholder: "내용 입력",
		height: 300,
		minHeight : 370,
		maxHeight: null
	});	
});

$("#subBtn").click(function(){
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
			data : {"subject" : $("#subject").val(), "content" : $("#summernote").val()},
			success : function(){
				alert("글쓰기 성공");
			}, 
			error : function(err) {
				console.log(err);
			}
		});
	}
});
</script>
<script type="text/javascript" src="../js/board.js"></script>

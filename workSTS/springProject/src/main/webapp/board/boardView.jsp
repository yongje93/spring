<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#subject { font-size: 30px; font-weight: bold;}
	pre { overflow: auto; width: 100%; height: 150px; white-space: pre-line; word-break: break-all; }
</style>

<form name="boardViewForm">
<input type="hidden" name="seq" id="seq" value="${seq}">
<input type="hidden" name="pg" id="pg" value="${pg}">
<table border="1" width="450" frame="hsides" rules="rows" cellspacing="0" cellpadding="5">
	<tr>
		<td id="subject" colspan="5"><span id="subjectSpan"></span></td>
	</tr>
	<tr>
		<td width="150">글번호 : <span id="seqSpan"></span></td>
		<td width="30"></td>
		<td width="150">작성자 : <span id="idSpan"></span></td>
		<td width="30"></td>
		<td width="90">조회수 : <span id="hitSpan"></span></td>
	</tr>
	<tr>
		<td id="content" colspan="5" valign="top">
		<pre><span id="contentSpan"></span></pre>
		</td>
	</tr>
	<tr>
</table>
</form>
<br>
<input type="button" value="목록" onclick="location.href='/springProject/board/boardList?pg=${pg}'">
<input type="button" value="답글" onclick="mode(3)">
<span id="updateAndDeleteSpan">
	<input type="button" value="글수정" onclick="mode(1)">
	<input type="button" value="글삭제" onclick="mode(2)">
</span>
<script type="text/javascript">
function mode(num) {
	if(num == 1) {
		document.boardViewForm.method = "post";
		document.boardViewForm.action = "/springProject/board/boardModifyForm";
		document.boardViewForm.submit();
	} else if(num == 2) {
		document.boardViewForm.method = "post";
		document.boardViewForm.action = "/springProject/board/boardDelete";
		document.boardViewForm.submit();
	} else if(num == 3) {
		document.boardViewForm.method = "post";
		document.boardViewForm.action = "/springProject/board/boardReplyForm";
		document.boardViewForm.submit();
	}
}
</script>
<script type="text/javascript" src="../js/board.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#updateAndDeleteSpan").hide();
	
	$.ajax({
		type : "post",
		url : "/springProject/board/getBoardView",
		data : {"seq" : $("#seq").val()},
		dataType: "json",
		success : function(data){
			//alert(JSON.stringify(data));
			
			$("#subjectSpan").text(data.boardDTO.subject);
			$("#seqSpan").text(data.boardDTO.seq);
			$("#idSpan").text(data.boardDTO.id);
			$("#hitSpan").text(data.boardDTO.hit);
			$("#contentSpan").html(data.boardDTO.content);
			
			if(data.boardDTO.id == "${memId}") 
				$("#updateAndDeleteSpan").show();
		},
		error : function(e){
			console.log(e);
			alert("실패");
		}
	});
});
</script>
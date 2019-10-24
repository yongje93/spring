<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#subject { font-size: 30px; font-weight: bold;}
	pre { overflow: auto; width: 100%; height: 150px; white-space: pre-line; word-break: break-all; }
</style>
<input type="hidden" id="seq" value="${seq}">
<input type="hidden" id="pg" value="${pg}">

<table border="1" width="450" frame="hsides" rules="rows" cellspacing="0" cellpadding="5">
	<tr>
		<td id="subject" colspan="5"><span id="subjectDiv"></span></td>
	</tr>
	<tr>
		<td width="150">글번호 : <span id="seqDiv"></span></td>
		<td width="30"></td>
		<td width="150">작성자 : <span id="idDiv"></span></td>
		<td width="30"></td>
		<td width="90">조회수 : <span id="hitDiv"></span></td>
	</tr>
	<tr>
		<td id="content" colspan="5" valign="top">
		<pre><span id="contentDiv"></span></pre>
		</td>
	</tr>
	<tr>
</table>
<br>
<input type="button" value="목록" onclick="location.href='/springProject/board/boardList?pg=${pg}'">
<input type="button" value="답글" onclick="location.href='/springProject/board/boardReplyForm?pseq=${boardDTO.seq}&pg=${pg }'">
<span id="updateAndDeleteSpan">
	<input type="button" value="글수정" onclick="location.href='/springProject/board/boardModifyForm?seq=${boardDTO.seq}&pg=${pg }'">
	<input type="button" value="글삭제" onclick="checkBoardDelete(${boardDTO.seq})">
</span>
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
			
			$("#subjectDiv").text(data.boardDTO.subject);
			$("#seqDiv").text(data.boardDTO.seq);
			$("#idDiv").text(data.boardDTO.id);
			$("#hitDiv").text(data.boardDTO.hit);
			$("#contentDiv").text(data.boardDTO.content);
			
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
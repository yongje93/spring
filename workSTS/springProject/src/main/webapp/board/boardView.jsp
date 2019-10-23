<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	#subject { font-size: 30px; font-weight: bold;}
	pre { overflow: auto; width: 100%; height: 150px; white-space: pre-line; word-break: break-all; }
</style>
<c:if test="${boardDTO != null }">
<table border="1" width="450" frame="hsides" rules="rows" cellspacing="0" cellpadding="5">
	<tr>
		<td id="subject" colspan="5">${boardDTO.subject}</td>
	</tr>
	<tr>
		<td width="150">글번호 : ${boardDTO.seq}</td>
		<td width="30"></td>
		<td width="150">작성자 : ${boardDTO.id}</td>
		<td width="30"></td>
		<td width="90">조회수 : ${boardDTO.hit}</td>
	</tr>
	<tr>
		<td id="content" colspan="5" valign="top">
		<pre>${boardDTO.content}</pre>
		</td>
	</tr>
	<tr>
</table>
<br>
<input type="button" value="목록" onclick="location.href='/miniproject/board/boardList.do?pg=${pg}'">
<input type="button" value="답글" onclick="location.href='/miniproject/board/boardReplyForm.do?pseq=${boardDTO.seq}&pg=${pg }'">
	<c:if test="${memId == boardDTO.id }">
		<input type="button" value="글수정" onclick="location.href='/miniproject/board/boardModifyForm.do?seq=${boardDTO.seq}&pg=${pg }'">
		<input type="button" value="글삭제" onclick="checkBoardDelete(${boardDTO.seq})">
	</c:if>
</c:if>
<script type="text/javascript" src="../js/board.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>답글쓰기</h2>
<form name="boardReplyForm" method="post" action="/miniproject/board/boardReply.do">
 <input type="hidden" name="pseq" value="${pseq}">
 <input type="hidden" name="pg" value="${pg}">
 <table border="1" cellspacing="0" cellpadding="5">
 	<tr>
 		<td width="80" align="center">제목</td>
 		<td width="200"><input type="text" id="subject" name="subject" style="width: 250px;"></td>
 	</tr>
 	<tr>
 		<td width="80" align="center">내용</td>
 		<td>
 		 <textarea id="content" name="content" cols="40" rows="10" style="resize: none"></textarea>
 		</td> 
 	</tr>
 	<tr>
 		<td colspan="2" align="center">
 			<input type="button" value="답글쓰기" onclick="checkBoard()">
 			<input type="reset" value="다시작성">
 		</td>
 	</tr>
 </table>
</form>
<script type="text/javascript" src="../js/board.js"></script>

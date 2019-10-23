<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>게시글 수정</h2>
<form name="boardModifyForm" method="post" action="/miniproject/board/boardModify.do">
 	<input type="hidden" name="seq" value="${seq }">	<!-- hidden은 form 안에 넣어야 넘어감 id속성은 안넘어감. name으로 해야됨 -->
	<input type="hidden" name="pg" value="${pg }"> 
	<table border="1" cellspacing="0" cellpadding="5">
	 	<tr>
	 		<td width="80" align="center">제목</td>
	 		<td width="200">
	 			<input type="text" id="subject" name="subject" style="width: 250px;" value="${boardDTO.subject}">
	 		</td>
	 	</tr>
	 	<tr>
	 		<td width="80" align="center">내용</td>
	 		<td>
	 		 <textarea id="content" name="content" cols="40" rows="10" style="resize: none">${boardDTO.content}</textarea>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td colspan="2" align="center">
	 			<input type="button" value="글수정" onclick="checkBoard()">
	 			<input type="reset" value="다시작성">
	 		</td>
	 	</tr>
	 </table>
</form>
<script type="text/javascript" src="../js/board.js"></script>

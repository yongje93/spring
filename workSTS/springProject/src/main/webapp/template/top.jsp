<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>
<img src="../image/brown.png" width="50" height="50" onclick="location.href='/springProject/main/index'" style="cursor: pointer;">
Spring을 이용한 미니프로젝트</h3>


<font size="2">
<c:if test="${memId != null}">
	<a href="/springProject/board/boardWriteForm">글쓰기</a>&emsp;
	
	<c:if test="${memId == 'admin' }">
		<a href="/springProject/imageboard/imageboardWriteForm">이미지등록</a>&emsp;
	</c:if>
	<a href="/springProject/imageboard/imageboardList?pg=1">이미지 목록</a>&emsp;
</c:if>
<a href="/springProject/board/boardList">목록</a>&emsp;
</font>

<br><br>
<c:if test="${memId == null}">
<div id="kakao_id_login" style="text-align: center">
	<a href="${kakaoUrl}">
		<img src="../image/kakao_account_login_btn_medium_narrow.png"/>
	</a>
</div>
</c:if>

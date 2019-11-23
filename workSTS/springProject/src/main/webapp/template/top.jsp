<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3>
<img src="../image/brown.png" width="50" height="50" onclick="location.href='/springProject/main/index'" style="cursor: pointer;">
Spring을 이용한 미니프로젝트</h3>

<sec:authorize access="isAnonymous()">
	<h5><a href='<c:url value="/member/loginForm"/>'>로그인</a>로그인 해주세요</h5>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<p>님, 반갑습니다.</p>
	<form action="<c:url value="/member/logout"/>" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">로그아웃</button>
	</form>
</sec:authorize>

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
		<img width="223" src="../image/kakao_account_login_btn_medium_narrow.png"/>
	</a>
</div>
<div id="naver_id_login" style="text-align: center">
	<a href="${naverUrl}">
	<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
	</a>
</div>
</c:if>

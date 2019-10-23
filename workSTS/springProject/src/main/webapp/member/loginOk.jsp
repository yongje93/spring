<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

${sessionScope.memName }님 로그인
<br><br>
<input type="button" value="로그아웃" onclick="location.href='/springProject/member/logout'">
<!-- <input type="button" id="logoutBtn" value="로그아웃"> -->
<input type="submit" id="modifyBtn" value="회원정보수정">
<script src="../js/member.js" type="text/javascript"></script>
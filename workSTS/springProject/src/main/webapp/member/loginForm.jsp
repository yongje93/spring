<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#loginForm { 
	font-size: 10pt; 
}
</style>
<form id="loginForm" name="loginForm" method="post" action="/springProject/member/login">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width="80">아이디</th>
			<td>
				<input type="text" name="id" id="loginId" placeholder="아이디 입력">
				<div id="loginIdDiv"></div>
			</td>
		</tr>
		<tr>
			<th width="80">비밀번호</th>
			<td>
				<input type="password" name="pwd" id="loginPwd" placeholder="비밀번호 입력">
				<div id="loginPwdDiv"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="loginBtn" value="로그인"> 
				<input type="button" value="회원가입" onclick="location.href='/springProject/member/writeForm'">
			</td>						
		</tr>
	</table>
	<div id="loginResultDiv" style="text-align: center;"></div>
</form>
<script src="../js/member.js" type="text/javascript"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="writeForm" name="writeForm" method="post" action="/springProject/member/register">
	<h1>회원가입</h1>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width="80">이름</th>
			<td>
				<input type="text" id="name" name="name" placeholder="이름 입력" size="15">
				<div id="nameDiv"></div>
			</td>
		</tr>
		<tr>
			<th width="80">아이디</th>
			<td>
				<input type="text" id="id" name="id" placeholder="아이디 입력" style="width: 200px;"> 
				<div id="idDiv"></div>
			</td>
		</tr>
		<tr>
			<th width="80">비밀번호</th>
			<td>
				<input type="password" id="pwd" name="pwd" style="width: 230px;">
				<div id="pwdDiv"></div>
			</td>
		</tr>
		<tr>
			<th width="80">재확인</th>
			<td>
				<input type="password" id="repwd" name="repwd" style="width: 230px;">
				<div id="repwdDiv"></div>
			</td>
		</tr>
		<tr>
			<th width="80">성별</th>
			<td>
				<input type="radio" name="gender" value="0" checked>남&nbsp;
				<input type="radio" name="gender" value="1">여
			</td>
		</tr>
		<tr>
			<th width="80">이메일</th>
			<td>
				<input type="text" name="email1" style="width: 120px;">&nbsp;@&nbsp; 
				<input type="text" name="email2" list="list" placeholder="직접입력"> 
				<datalist id="list">
					<option value="naver.com"></option>
					<option value="gmail.com"></option>
					<option value="daum.net"></option>
					<option value="nate.net"></option>
				</datalist>
			</td>
		</tr>
		<tr>
			<th width="80">핸드폰</th>
			<td>
				<select name="tel1" style="width: 55px;">
					<option value="010">010</option>
					<option value="016">016</option>
					<option value="019">019</option>
				</select> - 
				<input type="text" name="tel2" style="width: 50px;"> - 
				<input type="text" name="tel3" style="width: 50px;">
			</td>
		</tr>
		<tr>
			<th width="80">주소</th>
			<td>
				<input type="text" name="zipcode" id="daum_zipcode" style="width: 70px;" readonly> 
				<input type="button" id="postBtn" value="우편번호검색"><br> 
				<input type="text" name="addr1" id="daum_addr1" placeholder="주소" style="width: 350px;" readonly><br> 
				<input type="text" name="addr2" id="daum_addr2" placeholder="상세 주소"style="width: 350px;">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="writeBtn" value="회원가입"> 
				<input type="reset" id="resetBtn" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script src="../js/member.js" type="text/javascript"></script>
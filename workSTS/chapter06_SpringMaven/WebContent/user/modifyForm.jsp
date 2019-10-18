<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>
	<strong>수정할 아이디 입력</strong>
	<input type="text" id="searchId" name="searchId" placeholder="아이디를 입력하세요">
	<input type="button" id="searchBtn" value="검색">
<p>
	<div id="resultDiv"></div>
	<div id="tableDiv">
	<form id="modifyForm" action="/chapter06_SpringMaven/user/modify">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<td width="70" align="center">이름</td>
				<td>
					<input type="text" name="name" id="name">
					<div id="nameDiv"></div>
				</td>
			</tr>
			<tr>
				<td align="center">아이디</td>
				<td>
					<input type="text" name="id" id="id" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td>
					<input type="password" name="pwd" id="pwd">
					<div id="pwdDiv"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="modifyBtn" value="수정">
					<input type="reset" id="resetBtn" value="취소"> 
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#tableDiv").hide();
	
	$("#searchBtn").click(function(){
		$("#resultDiv").empty();
		$("#tableDiv").hide();
		
		if($("#searchId").val() == "") {
			$("#searchId").focus();
			$("#resultDiv").text("아이디를 입력하세요").css("color", "tomato").css("font-weight", "bold");
		} else {
			$.ajax({
				type	: "post",
				url		: "/chapter06_SpringMaven/user/getUser",
				data	: {"id" : $("#searchId").val()},
				dataType: "json",
				success : function(data){
					if(data.userDTO == null) {
						$("#resultDiv").text("찾고자 하는 아이디가 없습니다").css("color", "tomato").css("font-weight", "bold");
					} else {
						$("#tableDiv").show();
						$("#name").val(data.userDTO.name);
						$("#id").val(data.userDTO.id);
						$("#pwd").val(data.userDTO.pwd);
					}
				},
				error: function(e) {
					console.log(e);
					alert("실패 = "+e);
				}
			});
		}
	});
	
	$("#modifyBtn").click(function(){
		$("#nameDiv").empty();
		$("#pwdDiv").empty();
		
		if($("#name").val() == "") {
			$("#name").focus();
			$("#nameDiv").text("이름을 입력하세요").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
		} else if($("#pwd").val() == "") {
			$("#pwd").focus();
			$("#pwdDiv").text("비밀번호를 입력하세요").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
		} else {
			$.ajax({
				type	: "post",
				url		: "/chapter06_SpringMaven/user/modify",
				data	: $("#modifyForm").serialize(),
				success : function(){
					alert("수정 완료!!");
					location.href="http://localhost:8080/chapter06_SpringMaven/main/index.do";
				}
			
			});
		}
	});
	
	$("#resetBtn").click(function(){
		$("div").empty();
	});
});
</script>
</html>
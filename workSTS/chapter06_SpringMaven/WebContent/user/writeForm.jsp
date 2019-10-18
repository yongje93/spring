<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="writeForm" action="/chapter06_SpringMaven/user/write">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="70" align="center">이름</td>
			<td>
				<input type="text" name="name" id="name" placeholder="이름을 입력하세요">
				<div id="nameDiv"></div>
			</td>
		</tr>
		<tr>
			<td align="center">아이디</td>
			<td>
				<input type="text" name="id" id="id" placeholder="아이디를 입력하세요">
				<div id="idDiv"></div>
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
				<input type="button" id="writeBtn" value="등록">
				<input type="reset" id="resetBtn" value="취소"> 
			</td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#id").focus();
	
	$("#id").blur(function(){
		if($("#id").val() == "") {
			$("#idDiv").text("먼저 아이디를 입력하세요").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
			$("#id").focus();
		} else {
			$.ajax({
				type    : "post",
				url     : "/chapter06_SpringMaven/user/checkId",
				data    : {"id" : $("#id").val()},
				dataType: "json",
				success : function(data){
					if(data.exist) {
						$("#idDiv").text("아이디 사용 불가능").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
						$("#id").focus();
					} else {
						$("#idDiv").text("아이디 사용 가능").css("color", "blue").css("font-size", "8pt").css("font-weight", "bold");
					}
				}
			});
		}
	});
	
	$("#writeBtn").click(function(){
		$("#nameDiv").empty();
		$("#idDiv").empty();
		$("#pwdDiv").empty();
		
		if($("#name").val() == "") {
			$("#name").focus();
			$("#nameDiv").text("이름을 입력하세요").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
		} else if($("#id").val() == "") {
			$("#id").focus();
			$("#idDiv").text("아이디를 입력하세요").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
		} else if($("#pwd").val() == "") {
			$("#pwd").focus();
			$("#pwdDiv").text("비밀번호를 입력하세요").css("color", "tomato").css("font-size", "8pt").css("font-weight", "bold");
		} else {
			//$("#writeBtn").sumbit();
			$.ajax({
				type	: "post",
				url		: "/chapter06_SpringMaven/user/write",
				data	: $("#writeForm").serialize(),	//"name="+$("#name").val()+"&id="+$("#id").val()+"&pwd="+$("#pwd").val()
														//{"name" : $("#name").val(), "id" : $("#id").val(), "pwd" : $("#pwd").val() }
				success : function(){
					alert("등록 완료!!");
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
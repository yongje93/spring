<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#mainA{margin-left: 20px;}
#mainA:link {color: black; text-decoration: none;}
#mainA:visited {color: black; text-decoration: none;}
#mainA:hover {color: tomato; font-weight: bold; text-decoration: underline;}
#mainA:active {color: black; text-decoration: none; }
</style>
</head>
<body>
	<h3>*** 메인화면 ***</h3>
	<a id="mainA" href="/chapter06_SpringMaven/user/writeForm">입력</a><br>
	<a id="mainA" href="../user/list">출력</a><br>
	<a id="mainA" href="../user/modifyForm">수정</a><br>
	<a id="mainA" href="../user/deleteForm">삭제</a><br>
</body>
</html>
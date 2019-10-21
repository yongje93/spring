<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 #display {
 	vertical-align: top;
 	padding: 20px;
 }
 #left {
 	vertical-align: top;
 	padding: 10px;
 }
</style>
</head>
<body>
	<table border="1" width="100%" cellspacing="0" cellpadding="5">
	  <tr>
		<td colspan="2" align="center">
			<jsp:include page="../template/top.jsp"/>
		</td>
	  </tr>
	  <tr>
		<td id="left" width="300px" height="500">
			<jsp:include page="../template/left.jsp"/>
		</td>
		<td id="display"><!-- body 부분은 값이 고정이 아니다. 그때그때 뿌려주는 값으로 -->
			<jsp:include page="${display}"/>
		</td>
	  </tr>
	  <tr>
		<td colspan="2">
			<jsp:include page="../template/bottom.jsp"/>
		</td>
	  </tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />

<form name="" method="get" action="/springProject/member/checkId">
	${requestScope.id }는 사용 불가능
	<br><br>
	아이디 : <input type="text" name="id">
	<input type="submit" value="중복체크">
</form>

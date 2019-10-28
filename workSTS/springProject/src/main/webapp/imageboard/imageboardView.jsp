<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	pre { overflow: auto; width: 100%; height: 150px; white-space: pre-line; word-break: break-all; }
</style>
<span>
	<img src="../image/zoom.png" width="20" height="20" style="cursor: pointer;" onclick="bigImage('${imageboardDTO.image1}')">
</span>
<table border="1" cellspacing="0" cellpadding="5">
	<tr>
		<td rowspan="4">
			<img src="http://localhost:8080/miniproject/storage/${imageboardDTO.image1}" width="200" height="200">
		</td>
		<td width="100" align="center">상품명</td>
		<td width="100" align="center">${imageboardDTO.imageName }</td>
	</tr>
	<tr>
		<td align="center">단가</td>
		<td align="center"><fmt:formatNumber pattern="#,###원" value="${imageboardDTO.imagePrice }"/></td>
	</tr>
	<tr>
		<td align="center">개수</td>
		<td align="center"><fmt:formatNumber pattern="#,###개" value="${imageboardDTO.imageQty }"/></td>
	</tr>
	<tr>
		<td align="center">합계</td>
		<td align="center"><fmt:formatNumber pattern="#,###원" value="${imageboardDTO.imagePrice * imageboardDTO.imageQty }"/></td>
	</tr>
	<tr>
		<td colspan="3" height="200" valign="top">
			<pre>${imageboardDTO.imageContent }</pre>
		</td>
	</tr>
</table>
<br>
<input type="button" value="목록" onclick="location.href='/miniproject/imageboard/imageboardList.do?pg=${pg}'">
<script type="text/javascript" src="../js/imageboard.js"></script>
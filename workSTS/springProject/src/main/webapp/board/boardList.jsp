<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="../css/board.css">

<input type="hidden" id="pg" value="${pg}">

<table id="boardListTable" border="1" frame="hsides" rules="rows" cellspacing="0" cellpadding="5" style="word-break: break-all;">
	<tr>
		<th width="100">글번호</th>
		<th width="400">제목</th>
		<th width="120">작성자</th>
		<th width="150">작성일</th>
		<th width="80">조회수</th>
	</tr>
</table>
<br>
<div id="boardPagingDiv" style="float: left; width: 850px; text-align: center;"></div>
<br><br>
<div style="float: left; width: 850px; text-align: center;">
	<form name="searchFrom" method="post">
		<input type="hidden" name="pg" value="1">
		<select name="opt" id="opt" style="width: 80px;">
			<option value="subject">제목</option>
			<option value="id">작성자</option>
		</select> 
		<input type="text" id="condition" name="condition" value="${condition}" style="width: 180px;" placeholder="검색어입력"> 
		<input type="button" id="searchBtn" value="검색">
		<div id="searchDiv"></div>
	</form>
</div>
<script src="../js/board.js" type="text/javascript"></script>
<script type="text/javascript">
window.onload=function(){
	if("${opt}"=="subject" || "${opt}"=="id")
		document.getElementById("opt").value = "${opt}";
}

function boardSearch(pg) {
	location.href="/miniproject/board/boardSearch?pg="+pg+"&opt=${opt}&condition="+encodeURIComponent("${condition}");
}
</script>
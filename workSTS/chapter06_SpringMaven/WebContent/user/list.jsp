<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#searchDiv {
	margin-left: 15px;
	margin-top: 15px;
}
#resultDiv {
	margin-top: 10px;
	margin-left: 90px;
}
</style>
</head>
<body>
<img src="../image/brown.png" width="50" height="50" onclick="location.href='/chapter06_SpringMaven/main/index.do'" style="cursor: pointer;">
<table id="table" border="1" cellpadding="5" cellspacing="0">
	<tr>
		<th width="100">이름</th>
		<th width="100">아이디</th>
		<th width="100">비밀번호</th>
	</tr>
</table>
<div id="searchDiv">
<form name="searchForm">
	<select name="option" id="option">
		<option value="">--선택--</option>
		<option value="name">이름</option>
		<option value="id">아이디</option>
	</select>
	<input type="text" name="keyword" id="keyword" placeholder="검색어를 입력하세요">
	<input type="button" id="searchBtn" value="검색"><br>
	<div id="resultDiv"></div>
</form>
</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type	: "post",
		url		: "/chapter06_SpringMaven/user/getList",
		dataType: "json",	//클래스 객체 값 못가져옴. List를 받아와야되는데 객체라 안됨 -> list를 json으로 변경시켜서 보내야한다
		success : function(data){	//data로 json 들어옴
			//alert(JSON.stringify(data));
			$.each(data.list, function(index, items){	// index 0 items {"name" : "홍길동", "id" : "hong", "pwd" : "111"}
				$("<tr/>").append($("<td/>", {
					align: "center" ,
					text: items.name
				})).append($("<td/>", {
					align: "center" ,
					text: items.id	
				})).append($("<td/>", {
					align: "center" ,
					text: items.pwd
				})).appendTo("#table");
			});
		},
		error : function(e){
			console.log(e);
			alert("실패");
		}
	});
	
	
	$("#searchBtn").click(function(){
		if($("#option option:selected").val() == "") {
			$("#resultDiv").text("검색 옵션을 선택하세요!").css("font-size", "9pt").css("color", "tomato").css("font-weight", "bold");
		} else {
			$("#resultDiv").empty();
			
			if($("#keyword").val() == "") {
				$("#resultDiv").text("검색어를 입력하세요!").css("font-size", "9pt").css("color", "tomato").css("font-weight", "bold");
				$("#keyword").focus();
			} else {
				$.ajax({
					type : "post",
					url  : "/chapter06_SpringMaven/user/search",
					data : JSON.stringify({"option" : $("#option option:selected").val(), "keyword" : $("#keyword").val()}),
					contentType : "application/json; charset=UTF-8",
					dataType: "json",
					success : function(data) {
						//alert(JSON.stringify(data));
						$("#table tr:not(:first)").empty();
						//$("#table tr:gt(0)").empty();
						
						$.each(data.searchList, function(index, items){
							$("<tr/>").append($("<td/>", {
								align: "center" ,
								text: items.name
							})).append($("<td/>", {
								align: "center" ,
								text: items.id	
							})).append($("<td/>", {
								align: "center" ,
								text: items.pwd
							})).appendTo("#table");
						});
					}
				});
			}
		}
	});
});
</script>
</html>
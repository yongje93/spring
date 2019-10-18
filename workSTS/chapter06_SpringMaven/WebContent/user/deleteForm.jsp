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
	<strong>삭제할 아이디 입력</strong>
	<input type="text" id="searchId" name="searchId" placeholder="아이디를 입력하세요">
	<input type="button" id="searchBtn" value="검색">
<p>
	<div id="resultDiv"></div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$("#resultDiv").empty();
		
		if($("#searchId").val() == "") {
			$("#searchId").focus();
			$("#resultDiv").text("아이디를 입력하세요").css("color", "tomato").css("font-weight", "bold");
		} else {
			$.ajax({
				type    : "post",
				url     : "/chapter06_SpringMaven/user/getUser",
				data    : {"id" : $("#searchId").val()},
				dataType: "json",
				success : function(data){
					if(data.userDTO == null) {
						$("#resultDiv").text("찾고자 하는 아이디가 없습니다").css("color", "tomato").css("font-weight", "bold");
					} else {
						var result = confirm("삭제하시겠습니까??");
						if(result) {
							$.ajax({
								type    : "post",
								url     : "/chapter06_SpringMaven/user/delete",
								data    : {"id" : data.userDTO.id},
								success : function(){
									alert("삭제 완료!!");
									location.href="http://localhost:8080/chapter06_SpringMaven/main/index.do";
								}
							});
						}
					}
				}
			});
		}
	});
});
</script>
</html>
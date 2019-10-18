<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				$('<tr/>').append($('<td/>', {
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
});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	pre { overflow: auto; width: 100%; height: 150px; white-space: pre-line; word-break: break-all; }
</style>
<table border="1" cellspacing="0" cellpadding="5">
	<tr>
		<td rowspan="4">
			<img id="image1" width="200" height="200">
		</td>
		<td width="100" align="center">상품명</td>
		<td width="100" align="center"><span id="imageNameSpan"></span></td>
	</tr>
	<tr>
		<td align="center">단가</td>
		<td align="center"><span id="imagePriceSpan"></span></td>
	</tr>
	<tr>
		<td align="center">개수</td>
		<td align="center"><span id="imageQtySpan"></span></td>
	</tr>
	<tr>
		<td align="center">합계</td>
		<td align="center"><span id="imageTotalSpan"></span></td>
	</tr>
	<tr>
		<td colspan="3" height="200" valign="top">
			<pre><span id="imageContentSpan"></span></pre>
		</td>
	</tr>
</table>
<br>
<input type="button" value="목록" onclick="location.href='/springProject/imageboard/imageboardList.do?pg=${pg}'">
<script type="text/javascript" src="../js/imageboard.js"></script>
<script>
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "/springProject/imageboard/getImageboardView",
		data : "seq=${seq}",
		dataType : "json",
		success : function(data) {
			//alert(JSON.stringify(data));
			var total = data.imageboardDTO.imagePrice*data.imageboardDTO.imageQty;
			
			$("#image1").prop("src", "../storage/"+data.imageboardDTO.image1);
			$("#imageNameSpan").text(data.imageboardDTO.imageName);
			$("#imagePriceSpan").text(data.imageboardDTO.imagePrice.toLocaleString());
			$("#imageQtySpan").text(data.imageboardDTO.imageQty);
			$("#imageTotalSpan").text(total.toLocaleString());
			$("#imageContentSpan").text(data.imageboardDTO.imageContent);
			
		},
		error: function(err){
			console.log(err);
		}
		
	});
});

</script>

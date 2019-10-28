<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name="imageboardWriteForm" method="post" enctype="multipart/form-data" action="/miniproject/imageboard/imageboardWrite.do">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">상품코드</td>
			<td width="300"><input type="text" id="imageId" name="imageId" style="width: 230px;" value="img_"></td>
		</tr>
		<tr>
			<td align="center">상품명</td>
			<td><input type="text" id="imageName" name="imageName" style="width: 270px;" placeholder="상품명 입력"></td>
		</tr>
		<tr>
			<td align="center">단가</td>
			<td><input type="text" id="imagePrice" name="imagePrice" style="width: 200px;" placeholder="단가 입력"></td>
		</tr>
		<tr>
			<td align="center">갯수</td>
			<td><input type="text" id="imageQty" name="imageQty" style="width: 200px;" placeholder="갯수 입력"></td>
		</tr>
		<tr>
			<td align="center">내용</td>
			<td>
	 		 	<textarea id="imageContent" name="imageContent" cols="40" rows="15" style="resize: none" placeholder="내용입력"></textarea>
	 		</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="file" name="image1" size="50">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="이미지등록" onclick="checkImageboard()">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script src="../js/imageboard.js" type="text/javascript"></script>

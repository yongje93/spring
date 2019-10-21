//이미지 게시판 유효성 검사
function checkImageboard() {
	if(document.getElementById("imageId").value == "img_") {
		alert("상품코드를 입력하세요");
		document.getElementById("imageId").focus();
	} else if(document.getElementById("imageName").value == "") {
		alert("상품명을 입력하세요");
		document.getElementById("imageName").focus();
	} else if(document.getElementById("imagePrice").value == "") {
		alert("단가를 입력하세요");
		document.getElementById("imagePrice").focus();
	} else if(document.getElementById("imageQty").value == "") {
		alert("갯수를 입력하세요");
		document.getElementById("imageQty").focus();
	} else if(document.getElementById("imageContent").value == "") {
		alert("상품내용을 입력하세요");
		document.getElementById("imageContent").focus();
	} else
		document.imageboardWriteForm.submit();
}
//이미지 확대 
function bigImage(imageName) {
	var newWindow = window.open("", "", "width=500, height=500, left=500, top=250");
	var image = newWindow.document.createElement("img");
	image.setAttribute("src", "http://localhost:8080/miniproject/storage/"+imageName);
	image.setAttribute("width", "500");
	image.setAttribute("height", "500");
	newWindow.document.body.appendChild(image);
	
}
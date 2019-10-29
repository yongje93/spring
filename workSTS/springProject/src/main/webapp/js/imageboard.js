$("#imageboardWriteBtn").click(function(){
	$("#imageIdDiv").empty();
	$("#imageNameDiv").empty();
	$("#imagePriceDiv").empty();
	$("#imageQtyDiv").empty();
	$("#imageContentDiv").empty();
	$("#img1Div").empty();
	
	if($("#imageId").val() == "") {
		$("#imageIdDiv").text("상품코드를 입력하세요").css("color", "red").css("font-size", "8pt");
		$("#imageId").focus();
	} else if($("#imageName").val() == "") {
		$("#imageNameDiv").text("상품명을 입력하세요").css("color", "red").css("font-size", "8pt");
		$("#imageName").focus();
	} else if($("#imagePrice").val() == "") {
		$("#imagePriceDiv").text("단가를 입력하세요").css("color", "red").css("font-size", "8pt");
		$("#imagePrice").focus();
	} else if($("#imageQty").val() == "") {
		$("#imageQtyDiv").text("갯수를 입력하세요").css("color", "red").css("font-size", "8pt");
		$("#imageQty").focus();
	} else if($("#imageContent").val() == "") {
		$("#imageContentDiv").text("상품내용을 입력하세요").css("color", "red").css("font-size", "8pt");
		$("#imageContent").focus();
	} else {
		var formData = new FormData($('#imageboardWriteForm')[0]);
		
		$.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false, 
			url: '/springProject/imageboard/imageboardWrite',
			data: formData,
			success: function(){
				alert("이미지 등록 성공");
				//location.href='/springProject/imageboard/imageboardList';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

//이미지 확대 
function bigImage(imageName) {
	var newWindow = window.open("", "", "width=500, height=500, left=500, top=250");
	var image = newWindow.document.createElement("img");
	image.setAttribute("src", "http://localhost:8080/miniproject/storage/"+imageName);
	image.setAttribute("width", "500");
	image.setAttribute("height", "500");
	newWindow.document.body.appendChild(image);
	
}
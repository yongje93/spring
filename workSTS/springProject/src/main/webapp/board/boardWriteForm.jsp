<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

<h2>글쓰기</h2>
<!-- <form>
 <table border="1" cellspacing="0" cellpadding="5">
 	<tr>
 		<td width="80" align="center">제목</td>
 		<td width="200">
 			<input type="text" id="subject" name="subject" style="width: 250px;">
 			<div id="subjectDiv"></div>
 		</td>
 	</tr>
 	<tr>
 		<td width="80" align="center">내용</td>
 		<td>
 		 <textarea id="content" name="content" cols="40" rows="10" style="resize: none"></textarea>
 		 <div id="contentDiv"></div>
 		</td> 
 	</tr>
 	<tr>
 		<td colspan="2" align="center">
 			<input type="button" id="boardWriteBtn" value="글쓰기">
 			<input type="reset" value="다시작성">
 		</td>
 	</tr>
 </table>
</form> -->

<form id="summerForm" method="post" action="/summernotewrite">
	<input type="text" name="subject" id="subject" placeholder="제목">
	<div id="subjectDiv"></div>
	<br>
	<textarea id="summernote" name="content"></textarea>
	<div id="contentDiv"></div>
	<input type="button" id="subBtn" value="글작성">
</form>

<br><br>


<input type="text" id="address" placeholder="주소" readonly="readonly">
<input type="button" onclick="execDaumPostcode()" value="주소 검색"><br>
<div id="map" style="width: 400px; height: 400px; margin-top: 10px; display: none"></div>
<input type="text" id="buildingName">
<input type="text" id="address_y" readonly="readonly">
<input type="text" id="address_x" readonly="readonly">


<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89c3afe322de0763fb20750b2bf6b62a&libraries=services"></script>
<script>
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {					// y,       x
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수
				var buildingName = data.buildingName; // 건물명
				
				$("#buildingName").val(buildingName);
				
                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용
						
                        $("#address_y").val(result.y);
                        $("#address_x").val(result.x);
                        
                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
</script>


<script>
$(document).ready(function(){
	$("#summernote").summernote({
		placeholder: "내용 입력",
		height: 300,
		minHeight : 370,
		maxHeight: null
	});	
});

$("#subBtn").click(function(){
	if($("#subject").val() == "") {
		$("#subjectDiv").text("제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#subject").focus();
	} else if($("#content").val() == "") {
		$("#contentDiv").text("내용을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#content").focus();
	} else {
		$.ajax({
			type : "post",
			url : "/springProject/board/boardWrite",
			data : {"subject" : $("#subject").val(), "content" : $("#summernote").val()},
			success : function(){
				alert("글쓰기 성공");
			}, 
			error : function(err) {
				console.log(err);
			}
		});
	}
});
</script>
<script type="text/javascript" src="../js/board.js"></script>

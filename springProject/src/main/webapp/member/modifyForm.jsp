<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form id="modifyForm" name="modifyForm" method="post" action="/springProject/member/modify">
   <h1>회원정보수정</h1>
   <table border="1" cellspacing="0" cellpadding="5">
     <tr>
       <th width="80">이름</th>
       <td>
       	<input type="text" id="name" name="name" size="15" value="${memberDTO.name }">
        <div id="nameDiv"></div>
       </td>
     </tr>
     <tr>
       <th width="80">아이디</th>
       <td>
       	<input type="text" id="modifyId" name="id" value="${memberDTO.id }" style="width: 200px;" readonly>
       </td>
     </tr>
     <tr>
       <th width="80">비밀번호</th>
       <td>
       	<input type="password" id="pwd" name="pwd" style="width: 230px;">
       	<div id="pwdDiv"></div>
       </td>
     </tr>
     <tr>
       <th width="80">재확인</th>
       <td>
       	<input type="password" id="repwd" name="repwd" style="width: 230px;">
       	<div id="repwdDiv"></div>
       </td>
     </tr>
     <tr>
       <th width="80">성별</th>
       <td>
         <input type="radio" id="gender_0" name="gender" value="0">남&nbsp;
         <input type="radio" id="gender_1" name="gender" value="1">여
       </td>
     </tr>
     <tr>
       <th width="80">이메일</th>
       <td>
         <input type="text" name="email1" value="${memberDTO.email1 }" style="width: 120px;">
         &nbsp;@&nbsp;
         <input type="text" id="email22" name="email2" list="list">
         <datalist id="list">
           <option value="naver.com">naver.com</option>
           <option value="gmail.com">gmail.com</option>
           <option value="daum.net">daum.net</option>
           <option value="nate.net">nate.net</option>
         </datalist>
       </td>
     </tr>
     <tr>
       <th width="80">핸드폰</th>
       <td>
         <select id="tel11" name="tel1" style="width: 55px;">
           <option value="010">010</option>
           <option value="016">016</option>
           <option value="019">019</option>
         </select>
         -
         <input type="text" name="tel2" value="${memberDTO.tel2 }" style="width: 50px;">
         -
         <input type="text" name="tel3" value="${memberDTO.tel3 }" style="width: 50px;">
       </td>
     </tr>
     <tr>
       <th width="80">주소</th>
       <td>
         <input type="text" name="zipcode" id="daum_zipcode" value="${memberDTO.zipcode }" style="width: 70px;" readonly>
         <input type="button" id="postBtn" value="우편번호검색"><br>
         <input type="text" name="addr1" id="daum_addr1" placeholder="주소" value="${memberDTO.addr1 }" style="width: 350px;" readonly><br>
         <input type="text" name="addr2" id="daum_addr2" placeholder="상세 주소" value="${memberDTO.addr2 }" style="width: 350px;">
       </td>
     </tr>
     <tr>
       <td colspan="2" align="center">
         <input type="button" id="modifyBtn" value="회원정보수정">
         <input type="reset" value="다시작성">
       </td>
     </tr>
   </table>
</form>
<script type="text/javascript" src="../js/member.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#gender_"+"${memberDTO.gender}").prop("checked", true);
	$("#email22").val("${memberDTO.email2}");
	$("#tel11").val("${memberDTO.tel1}");	
});
</script>

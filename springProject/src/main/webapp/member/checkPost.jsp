<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
	td, option {font-size: 9pt;}
	#addressA:link {color: black; text-decoration: none;} 
	#addressA:visited {color: black; text-decoration: none;} 
	#addressA:hover {color: green; text-decoration: underline;}
	#addressA:active {color: black; text-decoration: none;}
</style>
<form name="postSearchForm" id="postSearchForm" method="post">
   <table id="postTable" border="1" width="100%" cellspacing="0" cellpadding="2">
     <tr>
       <td width="80" align="center">시도</td>
       <td>
       	<select id="sido" name="sido" style="width: 100px;">
      		<option>시도선택</option>
      		<option value="서울">서울</option>
			<option value="인천">인천</option>
			<option value="대전">대전</option>
			<option value="대구">대구</option>
			<option value="울산">울산</option>
			<option value="세종">세종</option>
			<option value="광주">광주</option>
			<option value="경기">경기</option>
			<option value="강원">강원</option>
			<option value="전남">전남</option>
			<option value="전북">전북</option>
			<option value="경남">경남</option>
			<option value="경북">경북</option>
			<option value="충남">충남</option>
			<option value="충북">충북</option>
			<option value="부산">부산</option>
			<option value="제주">제주</option>
      	</select>
      	<div id="sidoDiv"></div>
      </td>
      <td width="100" align="center">시.군.구</td>
      <td>
      	<input type="text" id="sigungu" name="sigungu" size="20">
      	<div id="sigunguDiv"></div>
      </td>
    </tr>
    <tr>
     <td width="80" align="center">도로명</td>
      <td colspan="3">
        <input type="text" id="roadname" name="roadname" size="30">
        <input type="button" id="postSearchBtn" value="검색">
        <div id="roadnameDiv"></div>
      </td>
    </tr>
    <tr>
      <td width="80" align="center">우편번호</td>
      <td colspan="3" align="center">주소</td>
    </tr>
  </table>
</form>    
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/member.js" type="text/javascript"></script>

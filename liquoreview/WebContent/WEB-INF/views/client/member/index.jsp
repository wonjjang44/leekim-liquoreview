<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src = "/resources/js/jquery-3.4.1.min.js"></script>
<script src = "/resources/js/bootstrap.js"></script>
<link rel="stylesheet" href = "/resources/css/boostrap.css">
<link rel="stylesheet" href = "/resources/css/bootstrap-theme.css">
<body>
<h2>liquoreview 사용자 메인 페이지 입니다.</h2>
<c:choose>
	<c:when test="${ loginSession == null}">
		<a href = "/member/login">로그인</a><br/>	
	</c:when>
	<c:otherwise>
		<h3>${loginSession.getUSERNAME() }님 환영합니다.</h3>
		<a href = "/member/logout">로그아웃</a><br/>
	</c:otherwise>
</c:choose>
<br/>
<div class = "container">
	<table class = "table">
		<thead>
			<tr>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach  items="${boardSession }" var="board">
					<td>[${board.getTAG_NAME() }]&nbsp; ${board.getMEMBER_ID() }</td>
					<td>${board.getUSER_NAME() }</td>
					<td>${board.getTITLE() }</td>
					<td>${board.getREGDATE() }</td>
					<td>${board.getHIT() }</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
</div>
</body>
<script>
$(function(){
	
});
</script>
</html>
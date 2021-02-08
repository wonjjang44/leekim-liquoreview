<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>liquoreview 사용자 메인 페이지 입니다.</h2>
<c:choose>
	<c:when test="${ loginSession == null}">
		<a href = "/member/login">로그인</a>	
	</c:when>
	<c:otherwise>
		<h3>${loginSession.getUSERNAME() }님 환영합니다.</h3>
		<a href = "/member/logout">로그아웃</a>
	</c:otherwise>
</c:choose>
</body>
</html>
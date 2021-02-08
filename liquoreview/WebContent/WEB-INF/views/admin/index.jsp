<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
<c:choose>
	<c:when test="${loginSession != null }">
		<h3>${loginSession.getUSERNAME() }님&nbsp;관리자 페이지에 오신걸 환영합니다.</h3>
		<a href = "/member/logout">로그아웃</a>
	</c:when>
</c:choose>
</body>
</html>
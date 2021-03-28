<%@page import="com.liquoreview.model.domain.Member"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	boolean flag = false;
	Member member = null;
	if (session.getAttribute("member") != null) {
		member = (Member) session.getAttribute("member");
		flag = true;
	}
%>
<title>Liquoreview &mdash; LeeKim's</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- favicon -->
<link rel="icon" href="<c:url value="/resources/images/icon/favicon.png"/>"/>
<link rel="shortcut icon" href="<c:url value="/resources/images/icon/favicon.png"/>"/>

<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700"	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="<c:url value="/resources/common/css/animate.css" />"/>
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="<c:url value="/resources/common/css/icomoon.css" />"/>
<!-- Bootstrap  -->
<link rel="stylesheet" href="<c:url value="/resources/common/css/bootstrap.css"/>"/>

<!-- Magnific Popup -->
<link rel="stylesheet" href="<c:url value="/resources/common/css/magnific-popup.css"/>"/>

<!-- Flexslider  -->
<link rel="stylesheet" href="<c:url value="/resources/common/css/flexslider.css"/>"/>

<!-- Theme style  -->
<link rel="stylesheet" href="<c:url value="/resources/common/css/style.css"/>"/>

<!-- Modernizr JS -->
<script src="<c:url value="/resources/common/js/modernizr-2.6.2.min.js"/>"></script>

<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="<c:url value="/resources/js/common/respond.min.js"/>"></script>
	<![endif]-->
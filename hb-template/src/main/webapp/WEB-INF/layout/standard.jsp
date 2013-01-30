<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE>
<html>
<head>
		<title><tiles:getAsString name="title" /> - Test </title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta content="" name="keywords">
		<meta content="" name="description">
		
		<!-- Import css/js -->
		<c:forEach items="${site.js }" var="js">
			<script src="${site.resourceServer}${js}" type="text/javascript"></script>
		</c:forEach>
		<style type="text/css">
			<c:forEach items="${site.css }" var="css">
		   		 @import url("${site.resourceServer}${css}");
			</c:forEach>
		</style>
		
</head>
<body>
	<tiles:insertAttribute name="header" ignore="true" />
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer" ignore="true" />
</body>
</html>
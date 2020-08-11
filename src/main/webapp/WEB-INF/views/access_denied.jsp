<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Denied</title>
<t:common_imports/>
</head>
<body>
	<div class="mt-5 mx-auto w-50 alert alert-primary text-center" role="alert">
	  Access denied - You are not authorized to access this source.<a href="${pageContext.request.contextPath}/" class="alert-link"> Click here to Home Page.</a>
	</div>
</body>
</html>
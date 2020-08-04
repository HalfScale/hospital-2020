<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Email Confirmation">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/confirm_registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/registration/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="mx-auto w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Confirm Registration</h5>
			
			<div class="alert alert-${status} text-center" role="alert">
			  ${message}
			</div>
			<a href="${pageContext.request.contextPath}/users/login"><button type="button" class="btn btn-primary">Login</button></a>
		</div>
	</jsp:body>
</t:system_page>
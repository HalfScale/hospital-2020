<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Complete">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/registration/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="text-center">
			<div class="alert alert-success w-50 mx-auto" role="alert">
				<span class="lead">Appointment successfully created. Please wait for the approval regarding the appointment date</span>
			</div>
		</div>
		
		<section class="text-center">
			 <a href="${pageContext.request.contextPath}/"><button class="btn btn-primary">Return to Homepage</button></a>
		</section>
	</jsp:body>
</t:system_page>
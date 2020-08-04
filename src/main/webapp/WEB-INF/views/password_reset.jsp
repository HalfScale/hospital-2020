<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Password Reset">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/registration/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
				
			<div class="form-group">
		    <label for="emailField">Email</label>
		    <input type="email" class="form-control" id="emailField" name="email" required/>
		   </div>
		
		 <button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</jsp:body>
</t:system_page>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/login/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/login/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
	
	<form:form action="${pageContext.request.contextPath}/authenticate_user"
			method="POST">
			
		<div class="form-group">
	    <label for="usernameField">Username</label>
	    <input type="text" class="form-control" id="usernameField" name="username"/>
	   </div>
	  
	  <div class="form-group">
	    <label for="passwordField">Password</label>
	    <input type="password" class="form-control" id="passwordField" name="password">
	  </div>
	  
  	  <button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
		
	</jsp:body>
</t:system_page>
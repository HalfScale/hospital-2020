<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Login">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/login/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/login/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
	
	<div>
		
		<c:if test="${not empty param['error']}">
			<div class="alert alert-danger text-center" role="alert">
			  <b>Invalid email and password!</b>
			</div>
		</c:if>
		
		<form class="needs-validation" action="${pageContext.request.contextPath}/authenticateUser" method="POST" novalidate>
				
			<div class="form-group">
		    <label for="usernameField">Email</label>
		    <input type="email" class="form-control" id="emailField" name="username" required/>
		    <div class="email-status">
        		
      		</div>
		   </div>
		  
		  <div class="form-group">
		    <label for="passwordField">Password</label>
		    <input type="password" class="form-control" id="passwordField" name="password" required/>
		    <div class="pass-status">
        		
      		</div>
		  </div>
		  
		  <div class="form-group">
			  <a href="${pageContext.request.contextPath}/password/reset/">Forgot Password</a>
		  </div>
		  
		 <div class="form-group">
			  <a href="${pageContext.request.contextPath}/registration">Sign Up</a>
		  </div>
		 
		 <button type="submit" class="submit-button btn btn-primary">Submit</button>
		 <input type="submit" class="dummy-submit" />
		</form>
	</div>
		
	</jsp:body>
</t:system_page>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/registration/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		
		<div>
			<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
					
				<div class="form-group">
			    <label for="password">Password</label>
			    <input type="email" class="form-control" id="password" name="password" required/>
			   </div>
			  
			  <div class="form-group">
			    <label for="confirmPassword">Confirm Password</label>
			    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required/>
			  </div>
			  
			 <button type="submit" class="btn btn-primary">Confirm</button>
			</form:form>
	  </div>
	</jsp:body>
</t:system_page>
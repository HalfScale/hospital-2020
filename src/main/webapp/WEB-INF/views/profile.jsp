<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
		<div class="container">
		 <h1 class="text-center">Hospital Name</h1>
		 <h5 class="text-center">${user.userType == 2 ? 'Doctor Information' : 'Patient Information'}</h5>
		 <c:if test="${not empty message}">
			<div id="flash-message" class="mx-auto text-center w-50 alert alert-success alert-dismissible fade show" role="alert">
			  ${message}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			</button>
			</div>
		</c:if>
		 <c:if test="${user.userType == 3}">
		  <div class="row">
		    <div class="col-sm text-center">
		      <section class="mb-2">
		    		<span class="mr-2 font-weight-bold">First Name:</span>
		    		<span class="text-muted lead">${user.userDetail.firstName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Last Name:</span>
		    		<span class="text-muted lead">${user.userDetail.lastName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Email Address:</span>
		    		<span class="text-muted lead">${user.email}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Mobile No:</span>
		    		<span class="text-muted lead">${user.userDetail.mobileNo}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Gender:</span>
		    		<span class="text-muted lead"> ${user.userDetail.gender eq '1' ? 'Male' : 'Female'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Address:</span>
		    		<span class="text-muted lead">${not empty user.userDetail.address ? user.userDetail.address : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Birthdate (yyyy-mm-dd):</span>
		    		<span class="text-muted lead">${not empty birthDate ? birthDate : 'N/A'}</span>
		    	</section>
		    </div>
		   </div>
		  </c:if>
		  
		  <c:if test="${user.userType == 2}">
		  	<div class="row text-center">
		    <div class="col-sm">
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">First Name:</span>
		    		<span class="text-muted lead">${user.userDetail.firstName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Last Name:</span>
		    		<span class="text-muted lead">${user.userDetail.lastName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Email Address:</span>
		    		<span class="text-muted lead">${user.email}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Mobile No:</span>
		    		<span class="text-muted lead">${user.userDetail.mobileNo}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Address:</span>
		    		<span class="text-muted lead">${not empty user.userDetail.address ? user.userDetail.address : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Birthdate (yyyy-mm-dd):</span>
		    		<span class="text-muted lead">${not empty birthDate ? birthDate : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Specialization:</span>
		    		<span class="text-muted lead">${doctor.specialization}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Gender:</span>
		    		<span class="text-muted lead"> ${user.userDetail.gender eq '1' ? 'Male' : 'Female'}</span>
		    	</section>
		    	
		    </div>
		    <div class="col-sm">
		   		<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">No. of years in service:</span>
		    		<span class="text-muted lead">${not empty user.userDetail.noOfYearsExperience ? user.userDetail.noOfYearsExperience : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Education:</span>
		    		<span class="text-muted lead">${not empty user.userDetail.education ? user.userDetail.education : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Schedule:</span>
		    		<span class="text-muted lead">${not empty user.userDetail.schedule ? user.userDetail.schedule : 'N/A'}</span>
		    	</section>
		    	<section>
		    		<span class="mr-2 font-weight-bold">Expertise/Services:</span>
		    		<blockquote class="text-muted lead"> 
		    			${not empty user.userDetail.expertise ? user.userDetail.expertise : 'N/A'}
		    		</blockquote>
		    	</section>
		    </div>
			</div>
		  </c:if>
		  
		 <section class="mt-3 text-center">
			<a href="${pageContext.request.contextPath}/"><button type="button" class="btn btn-primary mr-2">Back</button></a>
			<a href="${pageContext.request.contextPath}/users/edit/info"><button type="button" class="btn btn-primary mr-2">Edit</button></a>
		 </section>
		  </div>
	</jsp:body>
</t:system_page>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Patient Info">

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
		 <h5 class="text-center">${userInfo.userType == 2 ? 'Doctor Information' : 'Patient Information'}</h5>
		 <section class="text-center"><button type="button" class="btn btn-primary">Message</button></section>
		 
		 <c:if test="${userInfo.userType == 3}">
		  <div class="row">
		    <div class="col-sm text-center">
		      <section class="mb-2">
		    		<span class="mr-2 font-weight-bold">First Name:</span>
		    		<span class="text-muted lead">${userInfo.userDetail.firstName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Last Name:</span>
		    		<span class="text-muted lead">${userInfo.userDetail.lastName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Email Address:</span>
		    		<span class="text-muted lead">${userInfo.email}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Mobile No:</span>
		    		<span class="text-muted lead">${userInfo.userDetail.mobileNo}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Gender:</span>
		    		<span class="text-muted lead"> ${userInfo.userDetail.gender eq '1' ? 'Male' : 'Female'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Address:</span>
		    		<span class="text-muted lead">${not empty userInfo.userDetail.address ? userInfo.userDetail.address : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Birthdate (yyyy-mm-dd):</span>
		    		<span class="text-muted lead">${not empty birthDate ? birthDate : 'N/A'}</span>
		    	</section>
		    </div>
		   </div>
		  </c:if>
		  
		  <c:if test="${userInfo.userType == 2}">
		  	<div class="row text-center">
		    <div class="col-sm">
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">First Name:</span>
		    		<span class="text-muted lead">${userInfo.userDetail.firstName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Last Name:</span>
		    		<span class="text-muted lead">${userInfo.userDetail.lastName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Email Address:</span>
		    		<span class="text-muted lead">${userInfo.email}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Mobile No:</span>
		    		<span class="text-muted lead">${userInfo.userDetail.mobileNo}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Address:</span>
		    		<span class="text-muted lead">${not empty userInfo.userDetail.address ? userInfo.userDetail.address : 'N/A'}</span>
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
		    		<span class="text-muted lead"> ${userInfo.userDetail.gender eq '1' ? 'Male' : 'Female'}</span>
		    	</section>
		    	
		    </div>
		    <div class="col-sm">
		   		<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">No. of years in service:</span>
		    		<span class="text-muted lead">${not empty userInfo.userDetail.noOfYearsExperience ? userInfo.userDetail.noOfYearsExperience : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Education:</span>
		    		<span class="text-muted lead">${not empty userInfo.userDetail.education ? userInfo.userDetail.education : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Schedule:</span>
		    		<span class="text-muted lead">${not empty userInfo.userDetail.schedule ? userInfo.userDetail.schedule : 'N/A'}</span>
		    	</section>
		    	<section>
		    		<span class="mr-2 font-weight-bold">Expertise/Services:</span>
		    		<blockquote class="text-muted lead"> 
		    			${not empty userInfo.userDetail.expertise ? userInfo.userDetail.expertise : 'N/A'}
		    		</blockquote>
		    	</section>
		    </div>
			</div>
		  </c:if>
		  
		 <section class="mt-3 text-center">
			<a href="${pageContext.request.contextPath}/appointments"><button type="button" class="btn btn-primary mr-2">Back</button></a>
		 </section>
		  </div>
	</jsp:body>
</t:system_page>
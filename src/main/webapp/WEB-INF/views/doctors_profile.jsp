<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Doctor">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/doctors/js/doctors.details.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="container">
		<h1 class="text-center">Hospital Name</h1>
		<h5 class="text-center">Doctor Details</h5>
		<c:if test="${not empty message}">
			<div id="flash-message" class="mx-auto text-center w-50 alert alert-success alert-dismissible fade show" role="alert">
			  ${message}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			</button>
			</div>
		</c:if>
		<section class="text-right"><button type="button" class="btn btn-primary">Message</button></section>
		<div class="row text-center">
		    <div class="col-sm">
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">First Name:</span>
		    		<span class="text-muted lead">${doctorUser.userDetail.firstName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Last Name:</span>
		    		<span class="text-muted lead">${doctorUser.userDetail.lastName}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Email Address:</span>
		    		<span class="text-muted lead">${doctorUser.email}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Mobile No:</span>
		    		<span class="text-muted lead">${doctorUser.userDetail.mobileNo}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Birthdate (yyyy-mm-dd):</span>
		    		<span class="text-muted lead">${not empty doctorUser.userDetail.birthDate ? doctorUser.userDetail.birthDate : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Specialization:</span>
		    		<span class="text-muted lead">${doctor.specialization}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Gender:</span>
		    		<span class="text-muted lead"> ${doctorUser.userDetail.gender eq '1' ? 'Male' : 'Female'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">No. of years in service:</span>
		    		<span class="text-muted lead">${not empty doctorUser.userDetail.noOfYearsExperience ? doctorUser.userDetail.noOfYearsExperience : 'N/A'}</span>
		    	</section>
		    </div>
		    <div class="col-sm">
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Education:</span>
		    		<span class="text-muted lead">${not empty doctorUser.userDetail.education ? doctorUser.userDetail.education : 'N/A'}</span>
		    	</section>
		    	<section class="mb-2">
		    		<span class="mr-2 font-weight-bold">Schedule:</span>
		    		<span class="text-muted lead">${not empty doctorUser.userDetail.schedule ? doctorUser.userDetail.schedule : 'N/A'}</span>
		    	</section>
		    	<section>
		    		<span class="mr-2 font-weight-bold">Expertise/Services:</span>
		    		<blockquote class="text-muted lead"> 
		    			${not empty doctorUser.userDetail.expertise ? doctorUser.userDetail.expertise : 'N/A'}
		    		</blockquote>
		    	</section>
		    </div>
		</div>
		
		<section class="mt-3 text-center">
			<a href="../../doctors"><button type="button" class="btn btn-primary mr-2">Back</button></a>
			<button type="button" class="createBtn btn btn-primary">Create Appointment</button>
		</section>
		</div>
	</jsp:body>
</t:system_page>
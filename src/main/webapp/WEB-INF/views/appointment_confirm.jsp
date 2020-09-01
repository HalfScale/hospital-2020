<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Confirmation">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/appointments/js/appointment.confirm.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="container w-50 mx-auto">
		  <h1 class="text-center">Hospital Name</h1>
		  <h5 class="text-center">Appointment Information</h5>
		  <section>
		  	<label class="lead text-muted">Please confirm appointment if the details displayed below are correct:</label>
		  </section>
		  
		  <dl class="row">
		  	<dt class="col-sm-4">First Name:</dt>
		  	<dd class="col-sm-8">${user.userDetail.firstName}</dd>
		  	
		  	<dt class="col-sm-4">Last Name:</dt>
		  	<dd class="col-sm-8">${user.userDetail.lastName}</dd>
		  	
		  	<dt class="col-sm-4">Address:</dt>
		  	<dd class="col-sm-8">${address}</dd>
		  	
		  	<dt class="col-sm-4">Gender:</dt>
		  	<dd class="col-sm-8">${user.userDetail.gender == '1' ? 'Male' : 'Female'}</dd>
		  	
		  	<dt class="col-sm-4">First Time Hospital Visit?</dt>
		  	<dd class="col-sm-8">${hospitalVisit}</dd>
		  	
		  	<dt class="col-sm-4">Appointment Start Date:</dt>
		  	<dd class="col-sm-8">${startDate}</dd>
		  	
	      	<dt class="col-sm-4">Appointment Start Time:</dt>
	      	<dd class="col-sm-8">${startTime}</dd>
	      	
	      	<dt class="col-sm-4">Appointment End Date:</dt>
	      	<dd class="col-sm-8">${endDate}</dd>
	      	
	      	<dt class="col-sm-4">Appointment End Time:</dt>
	      	<dd class="col-sm-8">${endTime}</dd>
	      	
	      	<dt class="col-sm-4">Mobile No:</dt>
	      	<dd class="col-sm-8">${user.userDetail.mobileNo}</dd>
	      	
	      	<dt class="col-sm-4">Email:</dt>
	      	<dd class="col-sm-8">${user.email}</dd>
	      	
	      	<dt class="col-sm-4">Reason for Appointment:</dt>
	      	<dd class="col-sm-8">${reasonForAppointment}</dd>
		  </dl>
		  
		  <form action="${pageContext.request.contextPath}/appointments/processAppointment" method="POST">
		  	<input type="hidden" name="patientId" value="${user.id}"/>
		  	<input type="hidden" name="doctorId" value="${doctor}"/>
		  	<input type="hidden" name="firstName" value="${user.userDetail.firstName}"/>
		  	<input type="hidden" name="lastName" value="${user.userDetail.lastName}"/>
		  	<input type="hidden" name="address" value="${address}"/>
		  	<input type="hidden" name="gender" value="${user.userDetail.gender}"/>
		  	<input type="hidden" name="hospitalVisit" value="${hospitalVisit}"/>
		  	<input type="hidden" name="startDate" value="${appointmentStartDate}"/>
		  	<input type="hidden" name="startTime" value="${appointmentStartTime}"/>
		  	<input type="hidden" name="endDate" value="${appointmentEndDate}"/>
		  	<input type="hidden" name="endTime" value="${appointmentEndTime}"/>
		  	<input type="hidden" name="mobileNo" value="${user.userDetail.mobileNo}"/>
		  	<input type="hidden" name="email" value="${user.email}"/>
		  	<input type="hidden" name="appointmentReason" value="${reasonForAppointment}"/>
		  
		  	<input type="submit" class="dummy-submit" />
		  </form>
		  
		   <c:url value = "/appointments/add" var = "backLink">
			   <c:param name = "doctor" value = "${doctor}"/>
			   <c:param name = "address" value = "${address}"/>
			   <c:param name = "hospitalVisit" value = "${hospitalVisit}"/>
			   <c:param name = "startDate" value = "${startDate}"/>
			   <c:param name = "startTime" value = "${appointmentStartTime}"/>
			   <c:param name = "endDate" value = "${endDate}"/>
			   <c:param name = "endTime" value = "${appointmentEndTime}"/>
			   <c:param name = "appointmentReason" value = "${reasonForAppointment}"/>
		   </c:url>
		  <section class="text-center">
		  	<button type="button" class="confirmBtn btn btn-primary">Confirm</button>
		  	<a href="${backLink}"><button type="button" class="btn btn-primary">Back</button></a>
		  </section>
		</div>
	</jsp:body>
</t:system_page>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Appointment">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/appointments/js/appointment.add.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="mx-auto mt-4 w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Create Appointment</h5>
			
			<form action="add/confirm" method="GET" novalidate>
			
				<input type="hidden" name="doctor" value="${param.doctor}" />
			
				<div class="form-group">
					<label>First Name: <b>${user.userDetail.firstName}</b> </label>
				</div>
				
				<div class="form-group">
					<label>Last Name: <b>${user.userDetail.lastName}</b></label>
				</div>
				
				<c:if test="${not empty user.userDetail.address}">
					<label>Address: <b>${user.userDetail.address}</b></label>
					<input type="hidden" name="address" value="${user.userDetail.address}" /> 
				</c:if>
				
				<c:if test="${empty user.userDetail.address}">
					<div class="form-group">
						<label for="address">Address</label>
						<input id="address" class="form-control address" name="address" value="${not empty param.address ? param.address: ''}" required/>
						<div class="address-status">
        		
      					</div>
					</div>
				</c:if>
				
				<div class="form-group">
					<label>Gender: <b>${user.userDetail.gender eq '1' ? 'Male' : 'Female'}</b> </label>
					<label></label>
				</div>
				
				<div class="form-group">
					<div class="form-group form-check-inline">
						<label class="form-check-label">First Time Hospital Visit?</label>
					</div>
					
					<div class="form-group form-check-inline">
						<input type="radio" id="hospitalVisitYes" name="hospitalVisit" value="Yes" class="form-check-input" checked>
						<label class="form-check-label" for="hospitalVisitYes">Yes</label>
					</div>
					<div class="form-group form-check-inline">
						<input type="radio" id="hospitalVisitNo" name="hospitalVisit" value="No" class="form-check-input" ${param.hospitalVisit == 'No' ? 'checked' : ''}>
					<label class="form-check-label" for="hospitalVisitNo">No</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-start-date">Appointment Start Date</label>
					<input type="text" id="appointment-start-date" class="appointmentStartDate form-control" value="${param.startDate}" name="appointmentStartDate" placeholder="mm/dd/yyyy" required>
					<div class="appointment-start-date-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-start-time">Appointment Start Time</label>
					<input type="text" id="appointment-start-time" class="w-25 appointmentStartTime form-control" name="appointmentStartTime" required>
					<div class="appointment-start-time-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-end-date">Appointment End Date</label>
					<input type="text" id="appointment-end-date" class="appointmentStartDate form-control" value="${param.endDate}" name="appointmentEndDate" placeholder="mm/dd/yyyy" required>
					<div class="appointment-end-date-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-end-time">Appointment End Time</label>
					<input type="text" id="appointment-end-time" class="w-25 appointmentStartTime form-control" name="appointmentEndTime" value="01:00" required>
					<div class="appointment-end-time-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="address">Mobile No: <b>${user.userDetail.mobileNo}</b></label>
				</div>
				
				<div class="form-group">
					<label for="address">Email: <b>${user.email}</b></label>
				</div>
				
				<div class="form-group">
					<label for="reason-for-appointment">Reason for appointment:</label>
					<textarea id="reason-for-appointment" class="form-control reasonForAppointment" name="reasonForAppointment" maxlength="250" rows="3" required>${param.appointmentReason}</textarea>
					<div class="reason-for-appointment-status">
        		
      				</div>
				</div>
				
				<input type="submit" class="dummy-submit"/>
			</form>
			
			<section>
				<a href="${pageContext.request.contextPath}/doctor/details/${param.doctor}"><button type="button" class="btn btn-primary">Back</button></a>
				<button type="button" class="saveBtn btn btn-primary">Create</button>
			</section>
		</div>
	</jsp:body>
</t:system_page>
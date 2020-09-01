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
			src="${pageContext.request.contextPath}/resources/pages/appointments/js/appointment.edit.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="mx-auto mt-4 w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Create Appointment</h5>
			<input id="startTime" type="hidden" value="${appointment.appointmentDetail.appointmentStartTime}"/>
			<input id="endTime" type="hidden" value="${appointment.appointmentDetail.appointmentEndTime}"/>
			<form action="${pageContext.request.contextPath}/appointments/processAppointment" method="POST" novalidate>
				
				<input type="hidden" name="appointment" value="${appointment.id}" />
			
				<div class="form-group">
					<label>First Name: <b>${user.userDetail.firstName}</b> </label>
				</div>
				
				<div class="form-group">
					<label>Last Name: <b>${user.userDetail.lastName}</b></label>
				</div>
				
				<div class="form-group">
					<label for="address">Address</label>
					<input id="address" class="form-control address" name="address" value="${appointment.appointmentDetail.address}"/>
					<div class="address-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label>Gender: <b>${user.userDetail.gender eq '1' ? 'Male' : 'Female'}</b> </label>
					<label></label>
				</div>
				
				<div class="form-group">
					<div class="form-group form-check-inline">
						<label class="form-check-label">First Time Hospital Visit?</label>
					</div>
					
					<div class="form-group form-check-inline">
						<input type="radio" id="hospitalVisitYes" name="hospitalVisit" value="Yes" class="form-check-input" ${appointment.appointmentDetail.firstTime ? 'checked' : ''}>
						<label class="form-check-label" for="hospitalVisitYes">Yes</label>
					</div>
					<div class="form-group form-check-inline">
						<input type="radio" id="hospitalVisitNo" name="hospitalVisit" value="No" class="form-check-input" ${!appointment.appointmentDetail.firstTime ? 'checked' : ''}>
					<label class="form-check-label" for="hospitalVisitNo">No</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-start-date">Appointment Start Date</label>
					<input type="text" id="appointment-start-date" class="appointmentStartDate form-control" value="${startDate}" name="startDate" placeholder="mm/dd/yyyy" />
					<div class="appointment-start-date-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-start-time">Appointment Start Time</label>
					<input type="text" id="appointment-start-time" class="appointmentStartTime form-control" name="startTime" />
				</div>
				
				<div class="form-group">
					<label for="appointment-end-date">Appointment End Date</label>
					<input type="text" id="appointment-end-date" class="appointmentStartDate form-control" value="${endDate}" placeholder="mm/dd/yyyy" name="endDate" />
					<div class="appointment-end-date-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-end-time">Appointment End Time</label>
					<input type="text" id="appointment-end-time" class="appointmentStartTime form-control" name="endTime" />
				</div>
				
				<div class="form-group">
					<label for="address">Mobile No: <b>${user.userDetail.mobileNo}</b></label>
				</div>
				
				<div class="form-group">
					<label for="address">Email: <b>${user.email}</b></label>
				</div>
				
				<div class="form-group">
					<label for="reason-for-appointment">Reason for appointment:</label>
					<textarea id="reason-for-appointment" maxlength="250" class="form-control reasonForAppointment" name="appointmentReason" rows="3">${appointment.appointmentDetail.appointmentReason}</textarea>
					<div class="reason-for-appointment-status">
        		
      				</div>
				</div>
				
				<input type="submit" class="dummy-submit" />
			</form>
			
			<section>
				<a href="${pageContext.request.contextPath}/doctor/details/${appointment.doctorId}"><button type="button" class="btn btn-primary">Back</button></a>
				<button type="submit" class="saveBtn btn btn-primary">Update</button>
			</section>
		</div>
	</jsp:body>
</t:system_page>
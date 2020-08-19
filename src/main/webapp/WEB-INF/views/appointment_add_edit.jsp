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
			src="${pageContext.request.contextPath}/resources/pages/registration/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="mx-auto mt-4 w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Create Appointment</h5>
			
			<form action="confirm" method="POST">
			
				<div class="form-group">
					<label>First Name: </label>
					<label></label>
				</div>
				
				<div class="form-group">
					<label>Last Name: </label>
					<label></label>
				</div>
				
				<div class="form-group">
					<label for="address">Address</label>
					<input class="form-control address" name="address" required/>
				</div>
				
				<div class="form-group">
					<label for="address">Address</label>
					<input class="form-control address" name="address" required/>
				</div>
				
				<div class="form-group">
					<label>Gender: </label>
					<label></label>
				</div>
				
				<div class="form-group">
					<div class="form-group form-check-inline">
						<label class="form-check-label">First Time Hospital Visit?</label>
					</div>
					
					<div class="form-group form-check-inline">
						<input type="radio" id="hospitalVisitYes" class="form-check-input">
						<label class="form-check-label" for="hospitalVisitYes">Yes</label>
					</div>
					<div class="form-group form-check-inline">
						<input type="radio" id="hospitalVisitNo" class="form-check-input">
					<label class="form-check-label" for="hospitalVisitNo">No</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-start-date">Appointment Start Date</label>
					<input type="date" id="appointment-start-date" class="appointmentStartDate form-control" name="appointmentStartDate" min="1950-01-01" required>
				</div>
				
				<div class="form-group">
					<label for="appointment-start-time">Appointment Start Time</label>
					<input type="time" id="appointment-start-time" class="appointmentStartTime form-control" name="appointmentStartTime" value="01:00" required>
					<small class="form-text text-muted">Use directional keys or inputs to modify the time.</small>
				</div>
				
				<div class="form-group">
					<label for="appointment-end-date">Appointment End Date</label>
					<input type="date" id="appointment-end-date" class="appointmentStartDate form-control" name="appointmentEndDate" min="1950-01-01" required>
				</div>
				
				<div class="form-group">
					<label for="appointment-end-time">Appointment End Time</label>
					<input type="time" id="appointment-end-time" class="appointmentStartTime form-control" name="appointmentEndTime" value="01:00" required>
					<small class="form-text text-muted">Use directional keys or inputs to modify the time.</small>
				</div>
				
				<div class="form-group">
					<label for="address">Mobile No:</label>
					<input class="form-control address" name="address" required/>
				</div>
				
				<div class="form-group">
					<label for="address">Email:</label>
					<input class="form-control address" name="address" required/>
				</div>
				
				<div class="form-group">
					<label for="reason-for-appointment">Reason for appointment:</label>
					<textarea id="reason-for-appointment" class="form-control reasonForAppointment" name="reasonForAppointment" rows="3" required></textarea>
				</div>
				
				<a href="${pageContext.request.contextPath}/appointments"><button type="button" class="btn btn-primary">Back</button></a>
				<button type="submit" class="btn btn-primary">Save</button>
			</form>
		</div>
	</jsp:body>
</t:system_page>
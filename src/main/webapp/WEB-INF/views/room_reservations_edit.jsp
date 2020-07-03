<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Reservation">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/room_reservation/js/room-reservations.edit.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="mx-auto mt-4 w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Register Reservation</h5>

			<form id="reservationForm">
				<div class="form-group">
					<label for="reg-first-name">Room image</label>
				</div>
				
				<div class="form-group">
					<label for="add-room-code">Room Code</label>
					<input id="add-room-code" class="form-control roomCode" name="roomCode" required/>
				</div>

				<div class="form-group">
					<div class="form-group form-check-inline">
						<label class="form-check-label">Has Associated Appointment</label>
					</div>
					
					<div class="form-group form-check-inline">
						<input type="radio" id="associatedYes" class="form-check-input">
						<label class="form-check-label" for="associatedYes">Yes</label>
					</div>
					<div class="form-group form-check-inline">
						<input type="radio" id="associatedNo" class="form-check-input">
					<label class="form-check-label" for="associatedNo">No</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="appointment-id">Associated Appointment ID</label>
					<input type="text" id="appointment-id" class="form-control appointmentId" name="" required/>
				</div>
				
				<div class="form-group">
					<label for="reservation-date">Reservation Date</label>
					<input type="date" id="reservation-date" class="reservedDate form-control" name="reservedDate" min="1950-01-01" required>
				</div>
				
				<div class="form-group">
					<label for="appointment-id">Reservation Time</label>
					<input type="time" id="reservation-time" class="reservedTime form-control" name="reservedTime" value="01:00" step="2" required>
				</div>
				
				<div class="form-group">
					<label for="appointment-id">Reservation End Date</label>
					<input type="date" id="reservation-end-date" class="reservedEndDate form-control" name="reservedEndDate" min="1950-01-01" required>
				</div>
				
				<div class="form-group">
					<label for="appointment-id">Reservation End Time</label>
					<input type="time" id="reservation-end-time" class="reservedEndTime form-control" value="01:00" step="2" name="reservedEndTime" required>
				</div>

				<a href="${pageContext.request.contextPath}/reservations"><button type="button" class="btn btn-primary">Back</button></a>
				<button type="submit" class="btn btn-primary">Save</button>
			</form>
		</div>
	</jsp:body>
</t:system_page>
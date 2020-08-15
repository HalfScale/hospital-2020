<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/room_reservation/js/room-reservations.confirm.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div id="main-panel" class="mx-auto mt-4 w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Room Reservation Details</h5>
			
			<h3 class="text-center"><span class="roomName"></span></h3>
			
			<section class="button-pane">
			
			</section>
			
			<input type="hidden" class="hospitalRoom" value="${hospitalRoom}"/>
			
			<table id="detailsTable" class="table table-bordered w-100">
				<colgroup>
					<col>
					<col>
				</colgroup>
	            <tbody>
					<tr>
						<td>Room Code</td>
						<td class="roomCode">${roomCode}</td>
					</tr>
					<tr>
						<td>Has Associated Appointment</td>
						<td class="associatedAppointment"></td>
					</tr>
					<tr>
						<td>Associated Appointment ID</td>
						<td class="appointmentId"></td>
					</tr>
					<tr>
						<td>Reservation Date</td>
						<td class="reservedDate">${formattedReservedDate}</td>
					</tr>
					<tr>
						<td>Reservation Time</td>
						<td class="reservedTime">${formatedReservedTime}</td>
					</tr>
					<tr>
						<td>Reservation End Date</td>
						<td class="reservedEndTime">${formattedReservedEndDate}</td>
					</tr>
					<tr>
						<td>Reservation End Time</td>
						<td class="reservedEndDate">${formatedReservedEndTime}</td>
					</tr>
	            </tbody>
        	</table>
        	
        	<!-- Form to be submitted -->
        	<form class="hidden-widget" action="../processReservation" method="POST">
        		<input type="hidden" name="hospitalRoom" value="${hospitalRoom}"/>
        		<input type="hidden" name="roomCode" value="${roomCode}"/>
        		<input type="hidden" name="reservedDate" value="${reservedDate}"/>
        		<input type="hidden" name="reservedTime" value="${reservedTime}"/>
        		<input type="hidden" name="reservedEndDate" value="${reservedEndDate}"/>
        		<input type="hidden" name="reservedEndTime" value="${reservedEndTime}"/>
        		
        		<input type="submit" class="dummy-submit"/>
        	</form>
        	
        	<a href="../../reservations/add?room=${hospitalRoom}&roomCode=${roomCode}&reservedDate=${reservedDate}&reservedTime=${reservedTime}&reservedEndTime=${reservedEndTime}&reservedEndDate=${reservedEndDate}">
        		<button type="button" class="btn btn-primary m-1">Back</button>
        	</a>
        	<button type="button" class="confirmBtn btn btn-primary m-1">Confirm</button>
		</div>
	</jsp:body>
</t:system_page>
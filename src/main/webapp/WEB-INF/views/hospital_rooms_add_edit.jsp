<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/hospital_rooms/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/hospital_rooms/js/hospital-rooms.add.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="mx-auto mt-4 w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Register Room</h5>

			<form id="hospitalRoomForm">
				<div class="form-group">
					<label for="reg-first-name">Room image</label>
				</div>
				
				<div class="form-group">
					<label for="add-room-code">Room Code</label>
					<input id="add-room-code" class="form-control roomCode" name="roomCode" required/>
				</div>

				<div class="form-group">
					<label for="add-room-name">Room Name</label>
					<input id="add-room-name" class="form-control roomName" name="roomName" required/>
				</div>
				
				<div class="form-group">
					<label for="add-room-status">Room Status</label>
					<select id="add-room-status" class="form-control status" name="status">
						<option value="0">Available</option>
	      				<option value="1">Under Reservation</option>
	      				<option value="3">Not available</option>
					</select>
				</div>
				
				<!--  <div class="form-group">
					<label for="add-room-status">Room Status</label>
					<input id="add-room-status" class="form-control" name="status" required/>
				</div>-->
				
				<div class="form-group">
					<label for="add-room-description">Room Description</label>
					<textarea id="add-room-description" class="form-control description" name="description" rows="3" required></textarea>
				</div>

				<a href="${pageContext.request.contextPath}/hospital_rooms"><button type="button" class="btn btn-primary">Back</button></a>
				<a href=""><button type="button" class="viewBtn btn btn-primary">View</button></a>
				<button type="submit" class="btn btn-primary">Register</button>
			</form>
		</div>
	</jsp:body>
</t:system_page>
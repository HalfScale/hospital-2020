<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Hopsital Room">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/hospital_rooms/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/hospital_rooms/js/hospital-rooms.view.js"></script>
	</jsp:attribute>

	<jsp:body>
		<div class="mx-auto mt-4 w-50 shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Register Details</h5>
			
			<h3 class="text-center"><span class="roomName"></span></h3>
			
			<button class="reserveBtn btn btn-primary m-1">Reserve room</button>
			<button class="editBtn btn btn-primary m-1">Edit</button>
			
			<table id="detailsTable" class="table table-bordered w-100">
				<colgroup>
					<col>
					<col>
				</colgroup>
	            <tbody>
					<tr>
						<td>Room Code</td>
						<td class="roomCode"></td>
					</tr>
					<tr>
						<td>Room Status</td>
						<td class="status"></td>
					</tr>
					<tr>
						<td>Room Description</td>
						<td class="description"></td>
					</tr>
	            </tbody>
        	</table>
        	<button class="backBtn btn btn-primary m-1">Back</button>
		</div>
	</jsp:body>
</t:system_page>
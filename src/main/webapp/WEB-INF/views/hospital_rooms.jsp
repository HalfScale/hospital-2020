<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/hospital_rooms/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/hospital_rooms/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		<a href="hospital_rooms/add"><button id="addHospitalRoom" type="button" class="btn btn-primary m-1">Add Room</button></a>
		
		<table id="hospitalRoomTable" class="table table-bordered table-hover w-100">
            <thead>
                <tr>
                    <th scope="col">Room Code</th>
                    <th scope="col">Room Name</th>
                    <th scope="col">Created By</th>
                    <th scope="col">Updated By</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>

            <tbody>

            </tbody>
        </table>
	</jsp:body>
</t:system_page>
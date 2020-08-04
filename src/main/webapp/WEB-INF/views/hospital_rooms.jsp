<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Hospital Rooms">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/hospital_rooms/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/hospital_rooms/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		<c:if test="${not empty message}">
			<div id="flash-message" class="mx-auto text-center w-50 alert alert-${status} alert-dismissible fade show" role="alert">
			  ${message}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			</button>
			</div>
		</c:if>
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
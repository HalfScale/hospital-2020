<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/appointments/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/appointments/js/appointments.main.js"></script>
	</jsp:attribute>

	<jsp:body>
		<c:if test="${not empty message}">
			<div id="flash-message" class="mx-auto text-center w-50 alert alert-success alert-dismissible fade show" role="alert">
			  ${message}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			</button>
			</div>
		</c:if>
		<table id="appointmentList" class="table table-bordered table-hover w-100">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <c:if test="${user.userType < 3}">
	                    <th scope="col" id="type-hospital">Patient Name</th>
                    </c:if>
                    <c:if test="${user.userType == 3}">
	                    <th scope="col" id="type-patient">Doctor Name</th>
                    </c:if>
                    <th scope="col">Appointment Date and Time</th>
                    <th scope="col">Email</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>

            <tbody>
            </tbody>
       </table>
       <section class="text-center">
			<a href="${pageContext.request.contextPath}/"><button type="button" class="btn btn-primary pl-4 pr-4" >Back</button></a>	
       </section>
	</jsp:body>
</t:system_page>
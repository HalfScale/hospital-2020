<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/registration/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script
			src="${pageContext.request.contextPath}/resources/pages/doctors/js/doctors.main.js"></script>
	</jsp:attribute>

	<jsp:body>
		<table id="doctorsList" class="table table-bordered table-hover w-100">
            <thead>
                <tr>
                    <th scope="col">Doctor Name</th>
                    <th scope="col">Specialization</th>
                    <th scope="col">Description</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>

            <tbody>

            </tbody>
        </table>
        
        <section class="text-center">
			<a href="${pageContext.request.contextPath}/"><button type="button" class="btn btn-primary pl-4 pr-4">Back</button></a>	
       </section>
	</jsp:body>
</t:system_page>
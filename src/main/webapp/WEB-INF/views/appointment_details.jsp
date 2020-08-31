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
			src="${pageContext.request.contextPath}/resources/pages/appointments/js/appointment.details.js"></script>
	</jsp:attribute>

	<jsp:body>
	<div class="container w-50 mx-auto">
		<h1 class="text-center">Hospital Name</h1>
		<h5 class="text-center">Appointment Details</h5>
		
		<c:if test="${not empty message}">
			<div id="flash-message" class="mx-auto text-center w-50 alert alert-success alert-dismissible fade show" role="alert">
			  ${message}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			</button>
			</div>
		</c:if>
		
		<dl class="row">
		  <dt class="col-sm-4">First Name:</dt>
		  <dd class="col-sm-8">${firstName}</dd>
		  
		  <dt class="col-sm-4">Last Name:</dt>
		  <dd class="col-sm-8">${lastName}</dd>
		  
		  <dt class="col-sm-4">Address:</dt>
		  <dd class="col-sm-8">${address}</dd>
		  
		  <dt class="col-sm-4">Gender:</dt>
		  <dd class="col-sm-8">${gender == 1 ? 'Male' : 'Female'}</dd>
		  
		  <dt class="col-sm-4">First Time Hospital Visit?</dt>
		  <dd class="col-sm-8">${firstTime ? 'Yes' : 'No'}</dd>
		  
		  <dt class="col-sm-4">Appointment Start Date:</dt>
		  <dd class="col-sm-8">${startDate}</dd>
		  
		  <dt class="col-sm-4">Appointment Start Time:</dt>
		  <dd class="col-sm-8">${startTime}</dd>
		  
		  <dt class="col-sm-4">Appointment End Date:</dt>
		  <dd class="col-sm-8">${endDate}</dd>
		  
		  <dt class="col-sm-4">Appointment End Time:</dt>
		  <dd class="col-sm-8">${endTime}</dd>
		  
		  <dt class="col-sm-4">Mobile No:</dt>
		  <dd class="col-sm-8">${mobileNo}</dd>
		  
		  <dt class="col-sm-4">Email:</dt>
		  <dd class="col-sm-8">${email}</dd>
		  
		  <dt class="col-sm-4">Reason For Appointment:</dt>
		  <dd class="col-sm-8">${appointmentReason}</dd>
		  
		  <dt class="col-sm-4">Cancel Reason:</dt>
		  <dd class="col-sm-8">${not empty cancelReason ? cancelReason : 'N/A'}</dd>
		  
		
	  </dl>
	  
	  <section>
	  	<c:if test="${user.userType == 3}">
		  	<a href="../edit/${appointmentId}"}"><button type="button" class="btn btn-primary" ${status != 'pending' ? 'disabled' : ''}>Edit</button></a>
		  	<button type="button" class="cancelBtn btn btn-primary" data-toggle="modal" data-target="#cancelModal" ${status != 'pending' ? 'disabled' : ''}>Cancel Appointment</button>
	  	</c:if>
	  	
	  	<c:if test="${user.userType < 3}">
	  		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#approveModal" ${status != 'pending' ? 'disabled' : ''}>Approve</button>
		  	<button type="button" class="cancelBtn btn btn-primary" data-toggle="modal" data-target="#cancelModal" ${status != 'pending' ? 'disabled' : ''}>Reject Appointment</button>
	  	</c:if>
	  	
	  	<section class="mt-2">
		  	<a href="../../appointments" ><button type="button" class="btn btn-primary">Back</button></a>
	  	</section>
      </section>
	</div>
	
	<div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="cancelModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="cancelModalLabel">${user.userType == 2 ? 'Reject' : 'Cancel'} Appointment</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form action="${pageContext.request.contextPath}/appointments/cancel" method="POST">
	          <input type="hidden" name="appointmentId" value="${appointmentId}" />
	          <div class="form-group">
	            <label for="message-text" class="col-form-label">Reason for ${user.userType == 2 ? 'Rejection' : 'Cancellation'}</label>
	            <textarea class="form-control" id="message-text" name="cancelReason" required></textarea>
	          </div>
	          <input type="submit" class="dummy-submit" />
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <button type="button" class="submitBtn btn btn-primary">Submit</button>
	      </div>
	    </div>
	</div>
	</div>
	
	<div class="modal fade" id="approveModal" tabindex="-1" aria-labelledby="approveModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="approveModalLabel">Approve Appointment</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form action="${pageContext.request.contextPath}/appointments/approve" method="POST">
	          <input type="hidden" name="appointmentId" value="${appointmentId}" />
	          <div class="form-group">
	            <label for="message-text" class="col-form-label">Are you sure you want to approve the scheduled appointment?</label>
	          </div>
	          <input type="submit" class="dummy-submit" />
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="submitBtn btn btn-primary">Approve</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	      </div>
	    </div>
	</div>
	</div>
	
	
	</jsp:body>
</t:system_page>
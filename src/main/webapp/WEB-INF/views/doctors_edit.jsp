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
			src="${pageContext.request.contextPath}/resources/pages/registration/js/script.js"></script>
	</jsp:attribute>

	<jsp:body>
		<h1 class="text-center">Hospital Name</h1>
		<h5 class="text-center">Personal Information</h5>
		<form>
			<div class="container">
			  <div class="row">
			    <div class="col-sm">
			       <div class="form-group">
				    <label for="firstName">First Name</label>
				    <input type="text" class="form-control" id="firstName" name="firstName">
				  </div>
			       <div class="form-group">
				    <label for="lastName">Last Name</label>
				    <input type="text" class="form-control" id="lastName" name="lastName">
				  </div>
			       <div class="form-group">
				    <label for="email">Email Address</label>
				    <input type="email" class="form-control" id="email" name="email">
				  </div>
			       <div class="form-group">
				    <label for="mobileNo">Mobile No.</label>
				    <input type="text" class="form-control" id="mobileNo" name="mobileNo">
				  </div>
			       <div class="form-group">
				    <label for="mobileNo">Password</label>
				    <input type="password" class="form-control" id="password" name="password">
				  </div>
			       <div class="form-group">
				    <label for="address">Address.</label>
				    <input type="text" class="form-control" id="address" name="address">
				  </div>
			       <div class="form-group">
				    <label for="birthDate">BirthDate (dd/mm/yyyy)</label>
				    <input type="date" class="form-control" id="birthDate" name="birthDate" min="1950-01-01">
				  </div>
			    </div>
			    <div class="col-sm">
			    <div class="form-group">
			       <label>Gender</label>
					<div class="custom-control custom-radio">
						<input type="radio" id="gender-male"
							class="custom-control-input" name="gender" value="1" required checked/>
						<label class="custom-control-label" for="gender-male">Male</label>
					</div>
					<div class="custom-control custom-radio">
						<input type="radio" id="gender-female"
							class="custom-control-input" name="gender" value="2" />
						<label class="custom-control-label" for="gender-female">Female</label>
					</div>
			    </div>
			    
			    <div class="form-group">
				    <label for="specialization">Specialization</label>
				    <select id="specialization" class="specialization form-control" name="specialization">
				    	<option value=""></option>
				    	<option value="Internal Medicine">Internal Medicine</option>
				    	<option value="Pediatrician">Pediatrician</option>
				    	<option value="Surgeon">Surgeon</option>
				    	<option value="Obstetrician/Gynecologist">Obstetrician/Gynecologist</option>
				    	<option value="Cardiologist">Cardiologist</option>
				    	<option value="Gastroenterologist">Gastroenterologist</option>
				    	<option value="Neurologist">Neurologist</option></select>
				    </select>
				</div>
				
				<div class="form-group">
				    <label for="noOfYearsInService">No. Of Years in service</label>
				    <input type="text" class="form-control" id="noOfYearsInService" name="noOfYearsInService">
				 </div>
				 
				 <div class="form-group">
				    <label for="education">Education</label>
				    <input type="text" class="form-control" id="education" name="education">
				  </div>
				  
				  <div class="form-group">
				    <label for="schedule">Schedule</label>
				    <input type="text" class="form-control" id="schedule" name="schedule">
				  </div>
				  
				  <div class="form-group">
				    <label for="expertise">Expertise/Services</label>
				    <textarea class="form-control" id="expertise" rows="3" name="expertise"></textarea>
				  </div>
				  
			    </div>
			  </div>
			  
			  <section class="text-center">
			  	<a href="../../doctors"><button class="btn btn-primary" type="button">Back</button></a>
			  	<button class="btn btn-primary" type="submit">Update</button>
			  </section>
			</div>
		</form>
	</jsp:body>
</t:system_page>
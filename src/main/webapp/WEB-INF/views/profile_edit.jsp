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
			src="${pageContext.request.contextPath}/resources/pages/profile/js/profile.edit.js"></script>
	</jsp:attribute>

	<jsp:body>
		<!-- This page is dynamic since it adjusts based on the logged user -->
		<!-- For patient profile -->
		
		<div class="container w-50">
		<form action="${pageContext.request.contextPath}/processUserUpdate" method="POST" novalidate>
		 <h1 class="text-center">Hospital Name</h1>
		 <h5 class="text-center">Personal Information</h5>
		 
		 <!-- For patients -->
		 <c:if test="${user.userType == 3}">
		 	<div class="row">
		    <div class="col-sm">
		    	  <div class="form-group">
				    <label for="firstName">First Name</label>
				    <input type="text" class="form-control" id="firstName" name="firstName" value="${user.userDetail.firstName}" maxlength="50">
				    <div class="firstNameStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="lastName">Last Name</label>
				    <input type="text" class="form-control" id="lastName" name="lastName" value="${user.userDetail.lastName}" maxlength="50">
				    <div class="lastNameStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="email">Email Address</label>
				    <input type="email" class="form-control" id="email" name="email" value="${user.email}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
				    <div class="emailStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="mobileNo">Mobile No.</label>
				    <input type="text" class="form-control" id="mobileNo" name="mobileNo" value="${user.userDetail.mobileNo}"  minlength="11" maxlength="13">
				    <div class="mobileNoStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" id="password" name="password">
				    <div class="passwordStatus">
        		
      				</div>
				  </div>
			      <div class="form-group">
					<div class="form-group form-check-inline">
						<label class="form-check-label">Gender</label>
					</div>
					
					<div class="form-group form-check-inline">
						<input type="radio" id="genderMale" class="form-check-input" name="gender" value="1" ${user.userDetail.gender == 1 ? 'checked' : ''}>
						<label class="form-check-label" for="genderMale">Male</label>
					</div>
					<div class="form-group form-check-inline">
						<input type="radio" id="genderFemale" class="form-check-input" name="gender" value="2" ${user.userDetail.gender == 2 ? 'checked' : ''}>
					<label class="form-check-label" for="genderFemale">Female</label>
				</div>
				  </div>
			       <div class="form-group">
				    <label for="address">Address.</label>
				    <input type="text" class="form-control" id="address" name="address" value="${not empty user.userDetail.address ? user.userDetail.address : ''}">
				  </div>
			       <div class="form-group">
				    <label for="birthDate">BirthDate</label>
				    <input type="date" class="form-control" id="birthDate" name="birthDate" min="1950-01-01" value="${not empty user.userDetail.birthDate ? user.userDetail.birthDate : ''}">
				  </div>
				  
		    </div>
		  </div>
		 </c:if>
		 
		  <!-- For hospital -->
		 <c:if test="${user.userType == 2}">
		 	<div class="row">
			    <div class="col-sm">
			       <div class="form-group">
				    <label for="firstName">First Name</label>
				    <input type="text" class="form-control" id="firstName" name="firstName" value="${user.userDetail.firstName}" maxlength="50">
				    <div class="firstNameStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="lastName">Last Name</label>
				    <input type="text" class="form-control" id="lastName" name="lastName" value="${user.userDetail.lastName}" maxlength="50">
				    <div class="lastNameStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="email">Email Address</label>
				    <input type="email" class="form-control" id="email" name="email" value="${user.email}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
				    <div class="emailStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="mobileNo">Mobile No.</label>
				    <input type="text" class="form-control" id="mobileNo" name="mobileNo" value="${user.userDetail.mobileNo}"  minlength="11" maxlength="13">
				    <div class="mobileNoStatus">
        		
      				</div>
				  </div>
			       <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" id="password" name="password">
				  </div>
			       <div class="form-group">
				    <label for="address">Address.</label>
				    <input type="text" class="form-control" id="address" name="address" value="${not empty user.userDetail.address ? user.userDetail.address : ''}">
				  </div>
			       <div class="form-group">
				    <label for="birthDate">BirthDate</label>
				    <input type="date" class="form-control" id="birthDate" name="birthDate" min="1950-01-01" value="${not empty user.userDetail.birthDate ? user.userDetail.birthDate : ''}">
				  </div>
			    </div>
			    
			    <div class="col-sm">
			    <div class="form-group">
			       <label>Gender</label>
					<div class="custom-control custom-radio">
						<input type="radio" id="gender-male"
							class="custom-control-input" name="gender" value="1" ${user.userDetail.gender == 1 ? 'checked' : ''}/>
						<label class="custom-control-label" for="gender-male">Male</label>
					</div>
					<div class="custom-control custom-radio">
						<input type="radio" id="gender-female"
							class="custom-control-input" name="gender" value="2" ${user.userDetail.gender == 2 ? 'checked' : ''}/>
						<label class="custom-control-label" for="gender-female">Female</label>
					</div>
			    </div>
			    
			    <div class="form-group">
				    <label for="specialization">Specialization</label>
				    <select id="specialization" class="specialization form-control" name="specialization">
				    	<option value="1" ${doctor.doctorCode == 1 ? 'selected' : ''}>Internal Medicine</option>
				    	<option value="2" ${doctor.doctorCode == 2 ? 'selected' : ''}>Pediatrician</option>
				    	<option value="3" ${doctor.doctorCode == 3 ? 'selected' : ''}>Surgeon</option>
				    	<option value="4" ${doctor.doctorCode == 4 ? 'selected' : ''}>Obstetrician/Gynecologist</option>
				    	<option value="5" ${doctor.doctorCode == 5 ? 'selected' : ''}>Cardiologist</option>
				    	<option value="6" ${doctor.doctorCode == 6 ? 'selected' : ''}>Gastroenterologist</option>
				    	<option value="7" ${doctor.doctorCode == 7 ? 'selected' : ''}>Neurologist</option></select>
				    </select>
				</div>
				
				<div class="form-group">
				    <label for="noOfYearsInService">No. Of Years in service</label>
				    <input type="text" class="form-control" id="noOfYearsInService" name="noOfYearsInService" value="${not empty user.userDetail.noOfYearsExperience ? user.userDetail.noOfYearsExperience : ''}">
				 </div>
				 
				 <div class="form-group">
				    <label for="education">Education</label>
				    <input type="text" class="form-control" id="education" name="education" value="${not empty user.userDetail.education ? user.userDetail.education : ''}">
				  </div>
				  
				  <div class="form-group">
				    <label for="schedule">Schedule</label>
				    <input type="text" class="form-control" id="schedule" name="schedule" value="${not empty user.userDetail.schedule ? user.userDetail.schedule : ''}">
				  </div>
				  
				  <div class="form-group">
				    <label for="expertise">Expertise/Services</label>
				    <textarea class="form-control" id="expertise" rows="3" name="expertise">${not empty user.userDetail.expertise ? user.userDetail.expertise : ''}</textarea>
				  </div>
				  
			    </div>
			  </div>
		 </c:if>
		  
		  <input type="submit" class="dummy-submit" />
		  </form>
		  
		  <section class="text-center">
		  	<a href="${pageContext.request.contextPath}/"><button class="btn btn-primary" type="button">Back</button></a>
		  	<button class="updateBtn btn btn-primary" type="submit">Update</button>
		  </section>
	   </div>
	</jsp:body>
</t:system_page>
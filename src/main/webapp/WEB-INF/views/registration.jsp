<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

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
		<div id="registration-page" class="shadow p-3 mb-5 bg-white rounded">
			<h1 class="text-center">Hospital Name</h1>
			<h5 class="text-center">Registration</h5>

			<form id="registrationForm" novalidate>
				<div class="form-group">
					<label for="reg-first-name">First Name</label>
					<input type="text" id="reg-first-name" class="form-control" name="firstName" required/>
					<div class="first-name-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="reg-last-name">Last Name</label>
					<input type="text" id="reg-last-name" class="form-control" name="lastName" required/>
					<div class="last-name-status">
        		
      				</div>
				</div>

				<div class="form-group">
					<label for="reg-email">Email Address</label>
					<input type="email" id="reg-email" class="form-control" name="email" required/>
					<div class="email-status">
        		
      				</div>
				</div>

				<div class="form-group">
					<label>Gender</label>
					<div class="custom-control custom-radio">
						<input type="radio" id="reg-gender-male"
							class="custom-control-input" name="gender" value="1" required checked/>
						<label class="custom-control-label" for="reg-gender-male">Male</label>
					</div>
					<div class="custom-control custom-radio">
						<input type="radio" id="reg-gender-female"
							class="custom-control-input" name="gender" value="2" />
						<label class="custom-control-label" for="reg-gender-female">Female</label>
					</div>
				</div>

				<div class="form-group">
					<label for="reg-mobile">Mobile Number</label>
					<input type="text" id="reg-mobile" class="form-control" name="mobileNo" minlength="11" maxlength="13" required/>
					<div class="mobile-status">
        		
      				</div>
				</div>

				<div class="form-group">
					<label for="reg-password">Password</label>
					<input type="password" id="reg-password" class="form-control"
						minlength="6" maxlength="15" name="password" required/>
					<div class="pass-status">
        		
      				</div>
				</div>
				
				<div class="form-group">
					<label for="reg-password-confirm">Confirm Password</label>
					<input type="password" id="reg-password-confirm" class="form-control"
						name="passwordConfirm" minlength="6" maxlength="15" required/>
					<div class="confirm-pass-status">
        		
      				</div>
				</div>

				<div class="form-group">
					<label for="reg-doctor-code">Doctor Code</label>
					<input type="text" id="reg-doctor-code" class="form-control" name="doctorCodeId" />
					<div class="doctor-code-status">
        		
      				</div>
				</div>

				<div class="form-group">
					<label for="reg-terms-agreement">Terms of Agreement</label>
					<textarea class="form-control" id="reg-terms-agreement" rows="3"
						readonly>Pig hamburger biltong short loin landjaeger bacon. Pork loin pig tail ham jowl drumstick andouille beef pork belly jerky spare ribs brisket. Leberkas flank pork prosciutto meatball sausage beef ribs turkey andouille shankle ground round tri-tip bacon. Burgdoggen kevin ball tip capicola leberkas pig short ribs porchetta ham hock tail chicken fatback. Shoulder jerky frankfurter bacon buffalo, pig leberkas rump pork loin shank turkey filet mignon ham cow.</textarea>
				</div>

				<div class="form-group">
					<div class="custom-control custom-checkbox">
						<input id="reg-agree" type="checkbox" class="custom-control-input"
							required>
						<label class="custom-control-label" for="reg-agree">I agree to terms and agreement.</label>
						<div class="reg-agree-status">
        		
      					</div>
					</div>
				</div>

				<button type="submit" class="submit-button btn btn-primary">Register</button>
				<input type="submit" class="dummy-submit" />
				<a href="${pageContext.request.contextPath}/"><button type="button" class="return-bttn btn btn-primary">Return</button></a>
			</form>
		</div>
	</jsp:body>
</t:system_page>
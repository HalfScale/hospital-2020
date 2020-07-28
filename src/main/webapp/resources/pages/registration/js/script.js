$(function() {
	let emailElem = $('#reg-email');
	let emailStatus = $('.email-status');
	
	let doctorElem = $('#reg-doctor-code');
	let doctorStatus = $('.doctor-code-status');
	
	let firstNameElem = $('#reg-first-name');
	let firstNameStatus = $('.first-name-status');
	
	let lastNameElem = $('#reg-last-name');
	let lastNameStatus = $('.last-name-status');
	
	let mobileElem = $('#reg-mobile');
	let mobileStatus = $('.mobile-status');
	
	let passElem = $('#reg-password');
	let passStatus = $('.pass-status');
	
	let passConfirmElem = $('#reg-password-confirm');
	let passConfirmStatus = $('.confirm-pass-status');
	
	let termsElem = $('#reg-agree');
	let termsStatus = $('.reg-agree-status');
	
	$('#registrationForm').on('submit', function (e) {
		e.preventDefault();
		let form = $(this);
		let fd = form.serializeForm();
		
		let modified = {
			email: fd['email'],
			password: fd['password'],
			userType: fd['doctorCodeId'] ? 2 : 3,
			registrationToken: null,
			isConfirmed: false,
			enabled: false,
			userDetail: {
				firstName: fd['firstName'],
				lastName: fd['lastName'],
				mobileNo: fd['mobileNo'],
				gender: fd['gender'],
				doctorCodeId: fd['doctorCodeId'] ? fd['doctorCodeId'] : null
			}
		}
		
		console.log('form data', modified);
		
		let loading = sysLoad();
		$.ajax({
			type: "POST",
			url: 'api/users',
			data: JSON.stringify(modified),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			beforeSend: function () {
				loading.appendTo('body');
			},
			success: function (data) {
				console.log('success', data);
				loading.remove();
				
				sysAlert({
					text: 'Saving successful!',
					type: 'info'
				});
				
				form.trigger('reset');
				clearForm();
			}, 
			error: function () {
				loading.remove();
				sysAlert({
					text: errMsg,
					type: 'Unkown Error!'
				});
				clearForm();
			}
		});
	});
	
	$('.submit-button').on('click', function (e) {
		e.preventDefault();
		e.stopPropagation();
		
		let validateFirst = null;
		let emailExist = false;
		let isLoading = false;
		let isValid = false;

		let loading = sysLoad(); 
		
		
		
		if (firstNameElem.val() === '') {
			setStatus(firstNameStatus, 'invalid-feedback').text('First name is required!');
			removeFieldStatus(firstNameElem).addClass('is-invalid');
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-first-name' : validateFirst;
		}else {
			setStatus(firstNameStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(firstNameElem).addClass('is-valid');
			isValid = true;
		}
		
		if (lastNameElem.val() === '') {
			setStatus(lastNameStatus, 'invalid-feedback').text('Last name is required!');
			removeFieldStatus(lastNameElem).addClass('is-invalid');
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-last-name' : validateFirst;
		}else {
			setStatus(lastNameStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(lastNameElem).addClass('is-valid');
			isValid = true;
		}
		
		let isEmailValid = false;
		let isEmailAjaxValid = false;
		
		if (emailElem.val() === '') {
			setStatus(emailStatus, 'invalid-feedback').text('Email is required!');
			removeFieldStatus(emailElem).addClass('is-invalid');
			isEmailValid = false;
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-email' : validateFirst;
		}else if (!emailElem[0].checkValidity()) {
			setStatus(emailStatus, 'invalid-feedback').text('Invalid email format!');
			removeFieldStatus(emailElem).addClass('is-invalid');
			isEmailValid = false;
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-email' : validateFirst;
		}else {
			isEmailValid = true;
		}
		
		if (mobileElem.val() === '') {
			setStatus(mobileStatus, 'invalid-feedback').text('Mobile No. is required!');
			removeFieldStatus(mobileElem).addClass('is-invalid');
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-mobile' : validateFirst;
		}else if (!mobileElem[0].checkValidity()) {
			validateFirst = validateFirst == null ? 'reg-mobile' : validateFirst;
			
			if (!isMobileValid(mobileElem.val())) {
				setStatus(mobileStatus, 'invalid-feedback').text('Invalid Mobile No.');
				removeFieldStatus(mobileElem).addClass('is-invalid');
				
			}else {
				setStatus(mobileStatus, 'invalid-feedback').text('Mobile No. should be 11-13 digits!');
				removeFieldStatus(mobileElem).addClass('is-invalid');
			}
			
			isValid = false;
		}else if (!isMobileValid(mobileElem.val())) {
			setStatus(mobileStatus, 'invalid-feedback').text('Invalid Mobile No.!');
			removeFieldStatus(mobileElem).addClass('is-invalid');
			validateFirst = validateFirst == null ? 'reg-mobile' : validateFirst;
			isValid = false;
		}else {
			setStatus(mobileStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(mobileElem).addClass('is-valid');
			isValid = true;
		}
		
		if (passElem.val() === '') {
			setStatus(passStatus, 'invalid-feedback').text('Password is required!');
			removeFieldStatus(passElem).addClass('is-invalid');
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-password' : validateFirst;
		}else if (!passElem[0].checkValidity()) {
			setStatus(passStatus, 'invalid-feedback').text('Password should be 6-15 characters!');
			removeFieldStatus(passElem).addClass('is-invalid');
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-password' : validateFirst;
		}else{
			setStatus(passStatus, 'valid-feedback').text('looks good!');
			removeFieldStatus(passElem).addClass('is-valid');
			isValid = true;
		}
		
		if (passConfirmElem.val() === '') {
			setStatus(passConfirmStatus, 'invalid-feedback').text('Confirm password is required!');
			removeFieldStatus(passConfirmElem).addClass('is-invalid');
			validateFirst = validateFirst == null ? 'reg-password-confirm' : validateFirst;
			isValid = false;
		}else if (!passConfirmElem[0].checkValidity()) {
			setStatus(passConfirmStatus, 'invalid-feedback').text('Confirm password should be 6-15 characters!');
			removeFieldStatus(passConfirmElem).addClass('is-invalid');
			validateFirst = validateFirst == null ? 'reg-password-confirm' : validateFirst;
			isValid = false;
		}else if (passElem.val() !== passConfirmElem.val()) {
			setStatus(passConfirmStatus, 'invalid-feedback').text('Do not match!');
			removeFieldStatus(passConfirmElem).addClass('is-invalid');
			
			if (passElem.val() && passElem[0].checkValidity()) {
				setStatus(passStatus, 'invalid-feedback').text('Do not match!');
				removeFieldStatus(passElem).addClass('is-invalid');
			}
			validateFirst = validateFirst == null ? 'reg-password-confirm' : validateFirst;
			isValid = false;
		}else {
			setStatus(passConfirmStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(passConfirmElem).addClass('is-valid');
			isValid = true;
		}
		
		let isDoctorCodeValid = false;
		let isDoctorAjaxValid = false;
		
		if (doctorElem.val() !== ''){
			if (!isNumberValid(doctorElem.val())){
				setStatus(doctorStatus, 'invalid-feedback').text('Invalid hospital code!');
				removeFieldStatus(doctorElem).addClass('is-invalid');
				isDoctorCodeValid = false;
				isValid = false;
				validateFirst = validateFirst == null ? 'reg-doctor-code' : validateFirst;
			}else {
				isDoctorCodeValid = true;
			}
		}else {
			setStatus(doctorStatus, '').text('');
			removeFieldStatus(doctorElem);
			isValid = true;
			isDoctorAjaxValid = true;
		}
		
		if (!termsElem[0].checkValidity()) {
			setStatus(termsStatus, 'invalid-feedback').text('Please check Terms and Agreement to Register!');
			removeFieldStatus(termsElem).addClass('is-invalid');
			isValid = false;
			validateFirst = validateFirst == null ? 'reg-terms-agreement' : validateFirst;
		}else {
			setStatus(termsStatus, 'valid-feedback').text('');
			removeFieldStatus(termsElem).addClass('is-valid');
			isValid = true;
		}
		
		let doctorElemVal = isDoctorCodeValid ? doctorElem.val() : '0';
		let emailElemVal = isEmailValid ? emailElem.val() : '!';
		
		loading.appendTo($('body'));
		$.when($.getJSON('api/doctor_code/' + doctorElemVal), $.getJSON('api/users/email/' + emailElemVal + '/'))
		.then(function(doctor, user) {
			console.log('api/doctor_code/', doctor[0]);
			console.log('api/users/email/', user[0]);
			
			if (isDoctorCodeValid){
				
				if(doctor[0].id !== 0 && doctorElem.val() !== '') {
					setStatus(doctorStatus, 'valid-feedback').text('Looks good!');
					removeFieldStatus(doctorElem).addClass('is-valid');
					isDoctorAjaxValid = true;
				}else {
					setStatus(doctorStatus, 'invalid-feedback').text('Invalid hospital code!');
					removeFieldStatus(doctorElem).addClass('is-invalid');
					isDoctorAjaxValid = false;
					validateFirst = validateFirst == null ? 'reg-doctor-code' : validateFirst;
				}
				
			}
			
			if(isEmailValid) {
				
				if((user[0].id === 0)) {
					setStatus(emailStatus, 'valid-feedback').text('Email can be used.');
					removeFieldStatus(emailElem).addClass('is-valid');
					isEmailAjaxValid = true;
				}else {
					setStatus(emailStatus, 'invalid-feedback').text('Email is already used!');
					removeFieldStatus(emailElem).addClass('is-invalid');
					validateFirst = validateFirst == null ? 'reg-email' : validateFirst;
					isEmailAjaxValid = false;
				}
			}
			
			loading.remove();
			
			if(isValid && isDoctorAjaxValid && isEmailAjaxValid) {
				$('#registrationForm').find('.dummy-submit').click();
			}else {
				console.log('validateFirst', validateFirst);
				window.location.href = '#' + validateFirst;
			}
			
		});
		
	});

	function setStatus(elem, status) {
		return elem.removeClass('valid-feedback invalid-feedback').addClass(status);
	}
	
	function removeFieldStatus(elem) {
		return elem.removeClass('is-valid is-invalid');
	}
	
	function isNumberValid(num) {
		const pattern = /^[1-9][0-9]*$/;
		return num.match(pattern) != null;
	}
	
	function isMobileValid(num) {
		const pattern = /^[0-9]+$/;
		return num.match(pattern) != null;
	}
	
	function clearForm() {
		setStatus(emailStatus, '').text('');
		removeFieldStatus(emailElem);
		
		setStatus(doctorStatus, '').text('');
		removeFieldStatus(doctorElem);
		
		setStatus(firstNameStatus, '').text('');
		removeFieldStatus(firstNameElem);
		
		setStatus(lastNameStatus, '').text('');
		removeFieldStatus(lastNameElem);
		
		setStatus(mobileStatus, '').text('');
		removeFieldStatus(mobileElem);
		
		setStatus(passStatus, '').text('');
		removeFieldStatus(passElem);
		
		setStatus(passConfirmStatus, '').text('');
		removeFieldStatus(passConfirmElem);
		
		setStatus(termsStatus, '').text('');
		removeFieldStatus(termsElem);
	}
});
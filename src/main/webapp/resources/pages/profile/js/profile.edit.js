$(function () {
	const firstName = $('#firstName');
	const firstNameStatus = $('.firstNameStatus');
	
	const lastName = $('#lastName');
	const lastNameStatus = $('.lastNameStatus');
	
	const email = $('#email');
	const emailStatus = $('.emailStatus');
	
	const mobileNo = $('#mobileNo');
	const mobileNoStatus = $('.mobileNoStatus');
	
	const password = $('#password');
	const passwordStatus = $('.passwordStatus');
	
	$('.updateBtn').on('click', function(e) {
		e.preventDefault();
		e.stopPropagation();
		
		const validationElem = {
			'firstName': false,
			'lastName': false,
			'email': false,
			'mobileNo': false,
		}
		
		if (firstName.val() === '') {
			setStatus(firstNameStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(firstName).addClass('is-invalid');
		}else {
			setStatus(firstNameStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(firstName).addClass('is-valid');
			validationElem['firstName'] = true;
		}
		
		if (lastName.val() === '') {
			setStatus(lastNameStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(lastName).addClass('is-invalid');
		}else {
			setStatus(lastNameStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(lastName).addClass('is-valid');
			validationElem['lastName'] = true;
		}
		
		if (email.val() === '') {
			setStatus(emailStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(email).addClass('is-invalid');
		}else if (!email[0].checkValidity()) {
			setStatus(emailStatus, 'invalid-feedback').text('Invalid email format!');
			removeFieldStatus(email).addClass('is-invalid');
		}else {
			setStatus(emailStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(email).addClass('is-valid');
			validationElem['email'] = true;
		}
		
		if (mobileNo.val() === '') {
			setStatus(mobileNoStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(mobileNo).addClass('is-invalid');
		}else if (!mobileNo[0].checkValidity()) {
			
			if (!isMobileValid(mobileNo.val())) {
				setStatus(mobileNoStatus, 'invalid-feedback').text('Invalid Mobile No.');
				removeFieldStatus(mobileNo).addClass('is-invalid');
			}else {
				setStatus(mobileNoStatus, 'invalid-feedback').text('Limit of 11 to 13 Numbers!');
				removeFieldStatus(mobileNo).addClass('is-invalid');
			}
			
			removeFieldStatus(mobileNo).addClass('is-invalid');
		}else if (!isMobileValid(mobileNo.val())){
			setStatus(mobileNoStatus, 'invalid-feedback').text('Invalid Mobile No!');
			removeFieldStatus(mobileNo).addClass('is-invalid');
		}else {
			setStatus(mobileNoStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(mobileNo).addClass('is-valid');
			validationElem['mobileNo'] = true;
		}
		
		let validateFirst = null;
		let isValid = Object.keys(validationElem).every(function(key) {
			if (validationElem[key]) {
				return true;
			}else {
				validateFirst = key;
			}
		});
		
		if (isValid) {
			$('form').find('.dummy-submit').click();
		}else {
			window.location.href = '#' + validateFirst;
		}
		
	});
	
	function setStatus(elem, status) {
		return elem.removeClass('valid-feedback invalid-feedback').addClass(status);
	}
	
	function removeFieldStatus(elem) {
		return elem.removeClass('is-valid is-invalid');
	}
	
	function isMobileValid(num) {
		const pattern = /^[0-9]+$/;
		return num.match(pattern) != null;
	}
});
$(function() {
	$('.submit-button').on('click', function () {
		event.preventDefault();
		event.stopPropagation();
		
		let emailElem = $('#emailField');
		let emailStatus = $('.email-status');
		
		let passElem = $('#passwordField');
		let passStatus = $('.pass-status');
		
		if (emailElem.val() === '') {
			setStatus(emailStatus, 'invalid-feedback').text('Email is required!');
			removeFieldStatus(emailElem).addClass('is-invalid');
		}else if (!emailElem[0].checkValidity()) {
			setStatus(emailStatus, 'invalid-feedback').text('Invalid email format!');
			removeFieldStatus(emailElem).addClass('is-invalid');
		}else {
			setStatus(emailStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(emailElem).addClass('is-valid');
		}
		
		if (passElem.val() === '') {
			setStatus(passStatus, 'invalid-feedback').text('Password is required!');
			removeFieldStatus(passElem).addClass('is-invalid');
		}else {
			setStatus(passStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(passElem).addClass('is-valid');
		}
		
		if($('form')[0].checkValidity() === true) {
			console.log('process login!');
			$('.dummy-submit').click();
		}
	});
	
	function setStatus(elem, status) {
		return elem.removeClass('valid-feedback invalid-feedback').addClass(status);
	}
	
	function removeFieldStatus(elem) {
		return elem.removeClass('is-valid is-invalid');
	}

});
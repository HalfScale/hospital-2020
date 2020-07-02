$(function() {
	$('.return-bttn').on('click', function() {
		
	});
	
	$('#registrationForm').on('submit', function (e) {
		e.preventDefault();
		let form = $(this);
		let fd = form.serializeForm();
		
		let modified = {
			email: fd['email'],
			password: fd['password'],
			userType: fd['doctorCode'] != null ? fd['doctorCode'] : 3,
			userDetail: {
				firstName: fd['firstName'],
				lastName: fd['lastName'],
				mobileNo: fd['mobileNo'],
				gender: fd['gender']
			}
		};
		
		console.log('modified before submit', modified)
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
			}, 
			failure: function (errMsg) {
				console.log(errMsg)
				loading.remove();
				sysAlert({
					text: errMsg,
					type: 'danger'
				});
			}
		});
	});
});
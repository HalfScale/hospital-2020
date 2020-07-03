$(function() {
	let params = getParams();
	let hospitalRoom = null;
	let reservation = null;
	
	console.log('reservation params', params);
	
	if(params.room) {
		$.getJSON('../api/hospital_rooms/' + params.room).done(function(result) {
			hospitalRoom = result;
			console.log('api/hospital_rooms', result);
		});		
	}else {
		let path = window.location.pathname;
		let lastIndex = path.lastIndexOf("/");
		let id = path.substring(lastIndex + 1);
		
		$.getJSON('../../api/room_reservations/' + id).done(function(result) {
			reservation = result;
			form.fillForm({
				source: reservation
			});
			console.log('api/room_reservations', result);
		});		
		
	}
	
	
	const form = $('#reservationForm').on('submit', function(e) {
		e.preventDefault();
		let requestType = 'POST';
		let url = '../api/room_reservations';
		let form = $(this);
		let fd = form.serializeForm();
		
		if(reservation) {
			fd = $.extend(reservation, fd);
			url = '../../api/room_reservations'
			requestType = 'PUT';
		}else {
			//Needs to refactor if all things fall into place.
			fd['id'] = 0;
			fd['hospitalRoom'] = hospitalRoom;
			fd['reservedByUserId'] = '2';
			fd['reservationStatus'] = '0';
			fd['createdBy'] = 'Marwin Buenaventura';
			fd['updatedBy'] = 'Marwin Buenaventura';
		}
		
		console.log('fd before submit', fd);
		
		if(reservation) {
			let loading = sysLoad();
			$.ajax({
				type: requestType,
				url: url,
				data: JSON.stringify(fd),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				beforeSend: function () {
					loading.appendTo('body');
				},
				success: function (data) {
					console.log('success', data);
					
					loading.remove();
					
					form.trigger('reset');
					sysAlert({
						text: data.response,
						type: 'info'
					});
					
					window.location.href='../../reservations';
					
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
		}else {
			form.trigger('submit');
		}
		
	});
});
$(function() {
	let params = getParams();
	
	//This code is for when the path is on editing. '/edit/{id}'
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	const form = $('form');
	
	if(id != null && !id.includes('add')) {
		const loading = sysLoad().appendTo($('body'));
		$.getJSON('../../api/room_reservations/' + id).done(function(result) {
			console.log('api/room_reservations', result);
			
			loading.remove();
			form.attr('action', '../processReservation?id=' + result.id);
			
			form.fillForm({
				source: result,
				custom: function(form, data) {
					console.log('data', data);
					form.find('.hospitalRoom').val(data.hospitalRoom.id);
				}
			});
		});	
	}else {
		$('.hospitalRoom').val(params.room);
	}
	
	if (hasParams(params)) {
		$('#add-room-code').val(params['roomCode']);
		$('#reservation-date').val(params['reservedDate']);
		$('#reservation-time').val(params['reservedTime']);
		$('#reservation-end-date').val(params['reservedEndDate']);
		$('#reservation-end-time').val(params['reservedEndTime']);
	}
	
	function hasParams(params) {
		let flag = false;
		let paramList = ['roomCode', 'reservedDate', 'reservedTime', 'reservedEndTime', 'reservedEndDate'];
		
		paramList.every(function(key) {
			let result = params[key];
			console.log(key, result);
			//checks if each param are existing, if not the false
			if (result != null) {
				flag = true;
			}else {
				return false;
			}
		});
		
		return flag;
	}
});
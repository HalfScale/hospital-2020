$(function() {
	
	let current = null;
	
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	
	$.getJSON('../../api/room_reservations/' + id).done(function(result) {
		console.log('some result', result);
		
		current = result;
		
		$('.roomCode').text(result.roomCode);
		$('.associatedAppointment').text(result.hasAssociatedAppointment ? result.hasAssociatedAppointment : 'No');
		$('.appointmentId').text(result.associatedAppointmentId ? result.associatedAppointmentId : 'None');
		$('.reservedDate').text(result.reservedDate);
		$('.reservedTime').text(result.reservedTime);
		$('.description').text(result.hospitalRoom.description);
		
		if(result.reservationStatus == 0) {
			let buttonPaneTop = $('.button-pane-top');
			
			$('<button>').text('Cancel').addClass('cancelBtn btn btn-primary m-1').appendTo(buttonPaneTop);
			$('<button>').text('Done').addClass('doneBtn btn btn-primary m-1').appendTo(buttonPaneTop);
			
			$('<button>').text('Edit').addClass('editBtn btn btn-primary m-1').appendTo($('.button-pane-bottom'));
		}
	});
	
	$('#main-panel').on('click', '.cancelBtn', function() {
		
		let loading = sysLoad(); 
		sysConfirm({
			title: 'Cancel',
			text: 'Do you want to cancel this reservation?',
			ok: function(modal) {
				
				current['reservationStatus'] = 1;
				
				$.ajax({
					type: "PUT",
					url: '../../api/room_reservations',
					data: JSON.stringify(current),
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					beforeSend: function () {
						modal.modal('hide');
						loading.appendTo('body');
					},
					success: function (data) {
						console.log('success', data);
						window.location.href= '../../reservations'
					}
				});
			}
		
		});
		
	}).on('click', '.doneBtn', function() {
		
		let loading = sysLoad(); 
		current['reservationStatus'] = 2;

		$.ajax({
			type: "PUT",
			url: '../../api/room_reservations',
			data: JSON.stringify(current),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			beforeSend: function () {
				loading.appendTo('body');
			},
			success: function (data) {
				console.log('success', data);
				window.location.href= '../../reservations'
			}
		});
		
	}).on('click', '.editBtn', function() {
		window.location.href = '../edit/' + id;
	});
});
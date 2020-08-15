$(function() {
	let hospitalRoomId = $('.hospitalRoom').val();
	let hospitalRoom = null;
	
	$.getJSON('../../api/hospital_rooms/' + hospitalRoomId).done(function(result) {
		hospitalRoom = result;
		console.log('../api/hospital_rooms/ result', result);
	});
	
	$('.confirmBtn').on('click', function() {
		$('form').find('.dummy-submit').click();
//		let fd = {};
//		//Needs to refactor if all things fall into place.
//		let reservedTime = $('.reservedTime').text();
//		let reservedEndTime = $('.reservedEndTime').text();
//		
//		fd['id'] = 0;
//		fd['roomCode'] = $('.roomCode').text();
//		fd['hospitalRoom'] = hospitalRoom;
//		fd['reservedByUserId'] = '2';
//		fd['reservationStatus'] = '0';
//		fd['createdBy'] = 'Marwin Buenaventura';
//		fd['updatedBy'] = 'Marwin Buenaventura';
//		fd['reservedDate'] = $('.reservedDate').text();
//		fd['reservedTime'] = reservedTime.split(':').length > 2 ? reservedTime : reservedTime + ':00';
//		fd['reservedEndDate'] = $('.reservedEndDate').text();
//		fd['reservedEndTime'] = reservedEndTime.split(':').length > 2 ? reservedEndTime : reservedEndTime + ':00';
//		
//		
//		console.log('fd before submit', fd);
//		
//		let loading = sysLoad();
//		$.ajax({
//			type: "POST",
//			url: '../../api/room_reservations',
//			data: JSON.stringify(fd),
//			contentType: "application/json; charset=utf-8",
//			dataType: "json",
//			beforeSend: function () {
//				loading.appendTo('body');
//			},
//			success: function (data) {
//				console.log('success', data);
////				loading.remove();
////				
////				sysAlert({
////					text: 'Saving successful!',
////					type: 'info'
////				});
//				
//				window.location.href='../../reservations';
//			}
//		});
	});
});
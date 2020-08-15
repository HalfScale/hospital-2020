$(function() {
	
	let current = null;
	
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	
	$.getJSON('../../api/room_reservations/' + id).done(function(result) {
		console.log('some result', result);
		
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
				window.location.href='../../reservations/update/' + id + '?status=1';
//				current['reservationStatus'] = 1;
//				
//				$.ajax({
//					type: "PUT",
//					url: '../../api/room_reservations',
//					data: JSON.stringify(current),
//					contentType: "application/json; charset=utf-8",
//					dataType: "json",
//					beforeSend: function () {
//						modal.modal('hide');
//						loading.appendTo('body');
//					},
//					success: function (data) {
//						console.log('success', data);
//						window.location.href= '../../reservations'
//					}
//				});
			}
		
		});
		
	}).on('click', '.doneBtn', function() {
		window.location.href='../../reservations/update/' + id + '?status=2';
//		let loading = sysLoad(); 
//		current['reservationStatus'] = 2;
//
//		$.ajax({
//			type: "PUT",
//			url: '../../api/room_reservations',
//			data: JSON.stringify(current),
//			contentType: "application/json; charset=utf-8",
//			dataType: "json",
//			beforeSend: function () {
//				loading.appendTo('body');
//			},
//			success: function (data) {
//				console.log('success', data);
//				window.location.href= '../../reservations'
//			}
//		});
		
	}).on('click', '.editBtn', function() {
		window.location.href = '../edit/' + id;
	});
});
$(function () {
	
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	
	let selected = null;
	
	statusMap = {
		0: 'Available',
		1: 'Under Reservation',
		3: 'Not available'
	}
	
	if(id !== 'view') {
		$.ajax({
			type: 'GET',
			url: '../../api/hospital_rooms/' + id,
			contentType: 'application/json; charset=utf-8',
			dataType: "json",
			success: function (data) {
				selected = data;
				console.log('success', data);
				$('.roomName').text(data.roomName);
				$('.roomCode').text(data.roomCode);
				$('.status').text(statusMap[data.status]);
				$('.description').text(data.description);
			}
		});
	}else {
		let urlString = window.location.href;
		let url = new URL(urlString);
		
		let roomCode = url.searchParams.get('roomCode');
		let roomName = url.searchParams.get('roomName');
		let status = url.searchParams.get('status');
		let description = url.searchParams.get('description');
		
		$('.roomName').text(roomName);
		$('.roomCode').text(roomCode);
		$('.status').text(statusMap[status]);
		$('.description').text(description);
		
	}
	
	
	$('.editBtn').on('click', function() {
		window.location.href='../edit/' + selected.id;
	});
	
	$('.reserveBtn').on('click', function() {
		window.location.href='../../reservations/add?room=' + selected.id;
	});
});
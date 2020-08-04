$(function () {
	
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	
	let selected = null;
	const editBtn = $('.editBtn').prop('disabled', true);
	const reserveBtn = $('.reserveBtn').prop('disabled', true);
	
	statusMap = {
		0: 'Available',
		1: 'Under Reservation',
		3: 'Not available'
	}
	
	let urlString = window.location.href;
	let url = new URL(urlString);
	
	let roomCode = url.searchParams.get('roomCode');
	let roomName = url.searchParams.get('roomName');
	let status = url.searchParams.get('status');
	let description = url.searchParams.get('description');
	let isEdit = url.searchParams.get('edit');
	
	console.log('roomCode', roomCode);
	console.log('roomName', roomName);
	console.log('status', status);
	console.log('description', description);
	
	if(id !== 'view') {
		
		if(isEdit === 'true') {
			$('.roomName').text(roomName);
			$('.roomCode').text(roomCode);
			$('.status').text(statusMap[status]);
			$('.description').text(description);
		}else {
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
					
					if (haveRoomParams()) {
						editBtn.prop('disabled', true);
						reserveBtn.prop('disabled', true);
					}else {
						editBtn.prop('disabled', false);
						reserveBtn.prop('disabled', false);
					}
				}
			});
		}
		
	}else {
		$('.roomName').text(roomName);
		$('.roomCode').text(roomCode);
		$('.status').text(statusMap[status]);
		$('.description').text(description);
		
	}
	
	editBtn.on('click', function() {
		window.location.href='../edit/' + selected.id;
	});
	
	reserveBtn.on('click', function() {
		window.location.href='../../reservations/add?room=' + selected.id;
	});
	
	$('.backBtn').on('click', function() {
		
		if(url.searchParams.get('edit') === 'true'){
			linkViewer('../edit/' + id + '?'); // if there is an edit param, there's also an id.
		}else if (selected) {
			window.location.href='../../hospital_rooms';
		}else {
			linkViewer('../../hospital_rooms/add?');
		}
	});
	
	function linkViewer (link) {
		['roomCode', 'roomName', 'status', 'description'].forEach((param) => link += param + '=' + encode(url.searchParams.get(param)) + '&');
		window.location.href= link.substring(0, link.length - 1);
	}
	
	function haveRoomParams() {
		return roomCode != null && 
		roomName != null && 
		status != null && 
		description != null;
	}
	
	function encode(text) {
		return encodeURIComponent(text);
	}
});
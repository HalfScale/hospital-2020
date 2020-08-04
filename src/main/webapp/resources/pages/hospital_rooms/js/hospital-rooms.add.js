$(function() {
	
	let selected = null;
	
	let urlString = window.location.href;
	let url = new URL(urlString);
	
	let roomCode = url.searchParams.get('roomCode');
	let roomName = url.searchParams.get('roomName');
	let status = url.searchParams.get('status');
	let description = url.searchParams.get('description');
	
	if (haveRoomParams()) {
		$('#add-room-code').val(roomCode);
		$('#add-room-name').val(roomName);
		$('#add-room-status').val(status);
		$('#add-room-description').val(description);
	}
	
	const form = $('#hospitalRoomForm').on('', function(e) {
		e.preventDefault();
		let form = $(this);
		let fd = form.serializeForm();
		let type = selected ? 'PUT' : 'POST';
		let url = selected ? '../../api/hospital_rooms' : '../api/hospital_rooms';
		
		fd['createdBy'] = 'Marwin Buenaventura'; // Should fix this server side
		
		if(selected) {
			fd = $.extend(fd, {
				id: selected.id,
				createdBy: selected.createdBy
			});
		}
		
		console.log('modified before submit', fd)
		let loading = sysLoad(); 
		
		
		$.ajax({
			type: type,
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
				if (selected) {
					form.fillForm({
						source: data.data
					});
					selected = data.data;
				}else {
					window.location.href='edit/' + data.data.id;
				}
				
				sysAlert({
					text: data.response,
					type: 'info'
				});
				
			}, 
			error: function (errMsg) {
				loading.remove();
				sysAlert({
					text: 'Unknown error!',
					type: 'danger'
				});
			}
		});
	});
	
	//This code is for when the path is on editing. '/edit/{id}'
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	
	if(id != null && id != 'add') {
		form.attr('action', '../../hospital_rooms?id=' + id);
		
		$.ajax({
			type: "GET",
			url: '../../api/hospital_rooms/' + id,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (data) {
				console.log('edit room data', data);
				selected = data;
				
				form.fillForm({
					source: data,
				});
				
				if(haveRoomParams()) {
					form.fillForm({
						source: {
							roomCode: roomCode,
							roomName: roomName,
							description: description,
							status: status
						},
					});
				}
			}
		});
		
	}
	
	$('.viewBtn').on('click', function(e) {
		e.preventDefault();
		var fd = form.serializeForm();
		console.log('process view', fd);
		
		console.log('selected', selected);
		if(selected == null) {
			let url = 'details/view?';
			
			Object.keys(fd).forEach(function (key) {
				url += key + '=' + encodeURIComponent(fd[key]) + '&';
			});
			url = url.substring(0, url.length - 1);
			console.log('generated url', url);
			window.location.href= url;
		}else {
			let url = '../details/' + id + '?';
			
			Object.keys(fd).forEach(function (key) {
				url += key + '=' + encodeURIComponent(fd[key]) + '&';
			});
			
			url += 'edit=true';
			window.location.href= url;
		}
	});
	
	function haveRoomParams() {
		return roomCode != null && 
		roomName != null && 
		status != null && 
		description != null;
	}
	
});
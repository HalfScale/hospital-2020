$(function() {
	
	let selected = null;
	
	let urlString = window.location.href;
	let url = new URL(urlString);
	
	let roomCode = url.searchParams.get('roomCode');
	let roomName = url.searchParams.get('roomName');
	let status = url.searchParams.get('status');
	let description = url.searchParams.get('description');
	
	const form = $('#hospitalRoomForm');
	const hospitalDisplay = $('#hospitalImage');
	
	if (haveRoomParams()) {
		$('#add-room-code').val(roomCode);
		$('#add-room-name').val(roomName);
		$('#add-room-status').val(status);
		$('#add-room-description').val(description);
	}
	
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
					custom: function(form, data) {
//						hospitalDisplay.attr('title', data.roomImage);
//						$(this).next('.custom-file-label').text(data.roomImage);
//						
//						hospitalDisplay.attr('src', $g.file_path + '/' + data.roomImage).load(function() {
//							this.width;
//						});
//						reader.onload = function (e) {
//						};
						
//						reader.readAsDataURL(selectedFile);
					}
				});
				
				// Get the params instead if there are room params.
				if(haveRoomParams()) {
					form.fillForm({
						source: {
							roomCode: roomCode,
							roomName: roomName,
							description: description,
							status: status
						}
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
	
	$('#hospitalImageInput').on('change', function(e) {
		const selectedFile = e.currentTarget.files[0];
		const reader = new FileReader();
		
		var fileSize = ((selectedFile.size / 1024) / 1024).toFixed(4);
		console.log('fileSize', fileSize);
		
		if (fileSize > 2) {
			sysConfirm({
				title: 'Invalid file size',
				text: 'File size is too large!',
				hideConfirmBtn: true
			});
		}else {
			hospitalDisplay.attr('title', selectedFile.name);
			$(this).next('.custom-file-label').text(selectedFile.name);
			
			reader.onload = function (e) {
				hospitalDisplay.attr('src', e.target.result);
			};
			
			reader.readAsDataURL(selectedFile);
		}
	});
	
	function haveRoomParams() {
		return roomCode != null && 
		roomName != null && 
		status != null && 
		description != null;
	}
	
});
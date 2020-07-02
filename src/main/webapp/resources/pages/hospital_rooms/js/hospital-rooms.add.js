$(function() {
	
	let selected = null;
	
	const form = $('#hospitalRoomForm').on('submit', function(e) {
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
	
	//This code is for when the path is on editing.
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	
	if(id != null) {
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
			}
		});
	}
	
	$('.viewBtn').on('click', function(e) {
		e.preventDefault();
		var fd = form.serializeForm();
		console.log('process view', fd);
		
		if(selected == null) {
			let url = 'details/view?';
			
			Object.keys(fd).forEach(function (key) {
				url += key + '=' + fd[key] + '&';
			});
			url = url.substring(0, url.length - 1);
			window.location.href= url;
			console.log('generated url', url);
		}else {
			window.location.href = '../details/' + id;
		}
	});
	
});
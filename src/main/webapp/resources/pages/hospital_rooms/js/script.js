$(function () {
	const hospitalRoomTable = $('#hospitalRoomTable').DataTable({
		ajax: {
			url: 'api/hospital_rooms',
			dataSrc: ''
		},
		columns: [
			{data: 'roomCode'},
			{data: 'roomName'},
			{data: 'createdBy'},
			{data: 'createdBy'},
			{
				data: 'status',
				width: '7em',
				className: 'text-center',
				render: function(data) {
					if (data === 0) {
						return $('<span>', {
							class: 'badge badge-success',
							text: 'Available'
						}).prop('outerHTML');
					}
					
					if (data === 1) {
						return $('<span>', {
							class: 'badge badge-warning',
							text: 'Under Reservation'
						}).prop('outerHTML');
					}

					return $('<span>', {
						class: 'badge badge-danger',
						text: 'Unavailable'
					}).prop('outerHTML');
				}
			},
			{
                data: null,
				width: '10em',
				className: 'text-center',
                render: function (data) {
                	let btnContainer = $('<section>');
                	
                	$('<button>', {
                		id: data.id,
                		type: 'button',
                		class: 'deleteBtn btn btn-outline-danger btn-sm m-1',
                		text: 'Delete'
                	}).appendTo(btnContainer).data('room.id', data.id);
                	
                	let viewLink = $('<a>', {href: 'hospital_rooms/details/' + data.id}).appendTo(btnContainer);
                	$('<button>', {
                		type: 'button',
                		class: 'btn btn-outline-info btn-sm m-1',
                		text: 'View'
                	}).appendTo(viewLink);
                	
                	let editLink = $('<a>', {href: 'hospital_rooms/edit/' + data.id}).appendTo(btnContainer);
					$('<button>', {
						type: 'button',
						class: 'btn btn-outline-warning btn-sm m-1',
						text: 'Edit'
					}).appendTo(editLink);

					return btnContainer.prop('outerHTML');
                }
            }
		],
		createdRow: function (row, data) {
            $(row).data('room.data', data);
        }
	});
	
//	console.log('DataTable', hospitalRoomTable.ajax.reload());
	
	hospitalRoomTable.on('click', '.deleteBtn', function() {
		let roomData = $(this).parents('tr').data('room.data');
		
		let loading = sysLoad(); 
		sysConfirm({
			title: 'Delete',
			text: 'Do you want to delete this room?',
			ok: function(modal) {
				window.location.href = 'hospital_rooms/delete/' + roomData.id;
//				$.ajax({
//					type: "DELETE",
//					url: 'api/hospital_rooms/' + roomData.id,
//					contentType: "application/json; charset=utf-8",
//					dataType: "json",
//					beforeSend: function () {
//						$('#flash-message').remove(); // Remove if there is any previous flash message.
//						modal.modal('hide');
//						loading.appendTo('body');
//					},
//					success: function (data) {
//						console.log('success', data);
//						hospitalRoomTable.ajax.reload();
//						loading.remove();
//						
//						sysAlert({
//							text: data.response,
//							type: 'info',
//							delay: 2500
//						});
//						
//					}, 
//					error: function () {
//						loading.remove();
//						sysAlert({
//							text: 'Unknown error!',
//							type: 'danger',
//							delay: 2500
//						});
//					}
//				});
			}
		});
	});
});
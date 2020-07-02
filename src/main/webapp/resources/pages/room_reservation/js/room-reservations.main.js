$(function() {
	const reservationList = $('#reservationList').DataTable({
		ajax: {
			url: 'api/room_reservations',
			dataSrc: ''
		},
		columns: [
			{data: 'roomCode'},
			{data: 'hospitalRoom.roomName'},
			{data: 'createdBy'},
			{data: 'createdBy'},
			{
				data: 'reservationStatus',
				width: '7em',
				className: 'text-center',
				render: function(data) {
					if (data === 0) {
						return $('<span>', {
							class: 'badge badge-warning',
							text: 'Created'
						}).prop('outerHTML');
					}
					
					if (data === 1) {
						return $('<span>', {
							class: 'badge badge-danger',
							text: 'Cancelled'
						}).prop('outerHTML');
					}

					return $('<span>', {
						class: 'badge badge-success',
						text: 'Done'
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
                	
                	let viewLink = $('<a>', {href: 'reservations/details/' + data.id}).appendTo(btnContainer);
                	$('<button>', {
                		type: 'button',
                		class: 'btn btn-outline-info btn-sm m-1',
                		text: 'View'
                	}).appendTo(viewLink);
                	
                	let editLink = $('<a>', {href: 'reservations/edit/' + data.id}).appendTo(btnContainer);
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
            $(row).data('reservation.data', data);
        }
	});
	
	reservationList.on('click', '.deleteBtn', function() {
		let reservationData = $(this).parents('tr').data('reservation.data');
		
		let loading = sysLoad(); 
		sysConfirm({
			title: 'Delete',
			text: 'Do you want to delete this reservation?',
			ok: function(modal) {
				
				$.ajax({
					type: "DELETE",
					url: 'api/room_reservations/' + reservationData.id,
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					beforeSend: function () {
						modal.modal('hide');
						loading.appendTo('body');
					},
					success: function (data) {
						console.log('success', data);
						reservationList.ajax.reload();
						loading.remove();
						
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
			}
		});
	});
});
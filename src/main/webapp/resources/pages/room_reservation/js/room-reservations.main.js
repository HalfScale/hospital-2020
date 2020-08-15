$(function() {
	const reservationList = $('#reservationList').DataTable({
		initComplete: function (){
			var input = $('.dataTables_filter input[type=search]').unbind(),
            self = this.api(),
            $searchButton = $('<button>').addClass('mr-3 ml-3 btn btn-primary')
                       .text('Search')
                       .click(function() {
                		  if(selectFilter.val().trim() !== '') {
                			  filterColumn(4, '^' + selectFilter.val());
                		  }else {
                			  filterColumn(4,'');
                		  }
                		  if(nameInputFilter.val().trim() !== '') {
                			  filterColumn(1, nameInputFilter.val());
                		  }else {
                			  filterColumn(1,'');
                		  }
                		  if(codeInputFilter.val().trim() !== '') {
                			  filterColumn(0, codeInputFilter.val());
                		  }else {
                			  filterColumn(0,'');
                		  }
                       }),
            $clearButton = $('<button>').addClass('btn btn-primary')
                       .text('Clear')
                       .click(function() {
                    	   selectFilter.val('');
                    	   nameInputFilter.val('');
                    	   codeInputFilter.val('');
                       })
           input.parent().remove();
           const dataTablesFilter = $('.dataTables_filter').append($searchButton, $clearButton);
			
		   const statusFilter = $('<label>').text('Status:');
		   const selectFilter = $('<select>').addClass('statusFilter form-control form-control-sm').appendTo(statusFilter);
		   ['', 'Created', 'Cancelled', 'Done'].forEach((status) => $('<option>').text(status).val(status).appendTo(selectFilter));
		   statusFilter.prependTo(dataTablesFilter);
		   
		   const nameFilter = $('<label>').addClass('mr-2').text('Room Name:');
		   const nameInputFilter = $('<input>').addClass('nameFilter form-control form-control-sm').attr('type', 'text').appendTo(nameFilter);
		   nameFilter.prependTo(dataTablesFilter);
		   
		   const codeFilter = $('<label>').addClass('mr-2').text('Room Code:');
		   const codeInputFilter = $('<input>').addClass('codeFilter form-control form-control-sm').attr('type', 'text').appendTo(codeFilter);
		   codeFilter.prependTo(dataTablesFilter);
		},
		ajax: {
			url: 'api/room_reservations',
			dataSrc: ''
		},
		language: {
	        emptyTable: 'No Results Found.',
	        zeroRecords: 'No Results Found.',
	        loadingRecords: 'Loading...'
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
                	
                	if (data.reservationStatus !== 0) {
                		$('<button>', {
                			id: data.id,
                			type: 'button',
                			class: 'deleteBtn btn btn-outline-danger btn-sm m-1',
                			text: 'Delete'
                		}).appendTo(btnContainer).data('room.id', data.id);
					}
                	
                	
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
				console.log("Reservation to be deleted");
				window.location.href = 'reservations/delete/' + reservationData.id;
				
//				$.ajax({
//					type: "DELETE",
//					url: 'api/room_reservations/' + reservationData.id,
//					contentType: "application/json; charset=utf-8",
//					dataType: "json",
//					beforeSend: function () {
//						modal.modal('hide');
//						loading.appendTo('body');
//					},
//					success: function (data) {
//						console.log('success', data);
//						reservationList.ajax.reload();
//						loading.remove();
//						
//						sysAlert({
//							text: data.response,
//							type: 'info'
//						});
//						
//					}, 
//					failure: function (errMsg) {
//						console.log(errMsg)
//						loading.remove();
//						sysAlert({
//							text: errMsg,
//							type: 'danger'
//						});
//					}
//				});
			}
		});
	});
	
	function filterColumn(i, val) {
		$('#reservationList').DataTable().column(i).search(val, true, true).draw();
	}
});
$(function () {
	const hospitalRoomTable = $('#hospitalRoomTable').DataTable({
		initComplete: function() {
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
		   ['', 'Available', 'Unavailable', 'Under Reservation'].forEach((status) => $('<option>').text(status).val(status).appendTo(selectFilter));
		   statusFilter.prependTo(dataTablesFilter);
		   
		   const nameFilter = $('<label>').addClass('mr-2').text('Room Name:');
		   const nameInputFilter = $('<input>').addClass('nameFilter form-control form-control-sm').attr('type', 'text').appendTo(nameFilter);
		   nameFilter.prependTo(dataTablesFilter);
		   
		   const codeFilter = $('<label>').addClass('mr-2').text('Room Code:');
		   const codeInputFilter = $('<input>').addClass('codeFilter form-control form-control-sm').attr('type', 'text').appendTo(codeFilter);
		   codeFilter.prependTo(dataTablesFilter);
		},
		language: {
	        emptyTable: "No Results.",
	        zeroRecords: "No results."
	    },
	    order: [[0, 'asc']],
		ajax: {
			url: 'api/hospital_rooms',
			dataSrc: ''
		},
		columns: [
			{data: 'roomCode'},
			{data: 'roomName'},
			{data: 'createdBy'},
			{data: 'updatedBy'},
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
                	if (data.status === 0) {
                		$('<button>', {
                			id: data.id,
                			type: 'button',
                			class: 'deleteBtn btn btn-outline-danger btn-sm m-1',
                			text: 'Delete'
                		}).appendTo(btnContainer).data('room.id', data.id);
					}
                	
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
	
	hospitalRoomTable.on('click', '.deleteBtn', function() {
		let roomData = $(this).parents('tr').data('room.data');
		
		let loading = sysLoad(); 
		sysConfirm({
			title: 'Delete',
			text: 'Do you want to delete this room?',
			ok: function(modal) {
				window.location.href = 'hospital_rooms/delete/' + roomData.id;
			}
		});
	});
	
	function filterColumn(i, val) {
		$('#hospitalRoomTable').DataTable().column(i).search(val, true, true).draw();
	}
});
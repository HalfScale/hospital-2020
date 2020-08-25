$(function() {
	$('#appointmentList').DataTable({
		initComplete: function (){
			var input = $('.dataTables_filter input[type=search]').unbind(),
            self = this.api(),
            $searchButton = $('<button>').addClass('search-btn btn btn-primary')
                       .text('Search')
                       .click(function() {
                		  if(patientInputFilter.val().trim() !== '') {
                			  filterColumn(1, patientInputFilter.val());
                		  }else {
                			  filterColumn(1,'');
                		  }
                		  if(idInputFilter.val().trim() !== '') {
                			  filterColumn(0, idInputFilter.val());
                		  }else {
                			  filterColumn(0,'');
                		  }
                       }),
            $clearButton = $('<button>').addClass('btn btn-primary')
                       .text('Clear')
                       .click(function() {
                    	   patientInputFilter.val('');
                    	   idInputFilter.val('');
                       })
           input.parent().remove();
           const dataTablesFilter = $('.dataTables_filter').append($searchButton, $clearButton);
			
		   const patientFilter = $('<label>').addClass('mr-2').text('Patient Name:');
		   const patientInputFilter = $('<input>').addClass('nameFilter form-control form-control-sm').attr('type', 'text').appendTo(patientFilter);
		   patientFilter.prependTo(dataTablesFilter);
		   
		   const idFilter = $('<label>').addClass('mr-2').text('Appointment ID:');
		   const idInputFilter = $('<input>').addClass('codeFilter form-control form-control-sm').attr('type', 'text').appendTo(idFilter);
		   idFilter.prependTo(dataTablesFilter);
		},
		ajax: {
			url: 'api/appointment_details',
			dataSrc: ''
		},
		language: {
	        emptyTable: 'No Results Found.',
	        zeroRecords: 'No Results Found.',
	        loadingRecords: 'Loading...'
	    },
	    columns: [
	    	{data: 'appointmentDetail.appointment.id'},
	    	{
	    		data: null,
	    		render: function(data) {
	    			//If the logged in user is of type patient then display the name of the doctor
	    			if ($('#type-patient').length > 0) {
						return data.doctor.userDetail.firstName + ' ' + data.doctor.userDetail.lastName;
					}
	    			return data.appointmentDetail.firstName + ' ' + data.appointmentDetail.lastName
	    		}
	    	},
	    	{
	    		data: null,
	    		render: function(data) {
	    			return new Date(data.appointmentDetail.appointmentDate + ' ' + data.appointmentDetail.appointmentTime).format('mm/dd/yyyy HH:MM');
	    		},
	    	},
	    	{data: 'appointmentDetail.email'},
	    	{
	    		data: 'appointmentDetail.appointment.appointmentStatus',
	    		width: '7em',
				className: 'text-center',
				render: function(data) {
					if (data === 'pending') {
						return $('<span>', {
							class: 'badge badge-warning',
							text: 'Pending'
						}).prop('outerHTML');
					}
					
					if (data === 'approved') {
						return $('<span>', {
							class: 'badge badge-success',
							text: 'Approved'
						}).prop('outerHTML');
					}
					
					return $('<span>', {
						class: 'badge badge-success',
						text: data === 'cancelled-patient' ? 'Cancelled-Patient' : 'Cancelled-Hospital'
					}).prop('outerHTML');
				}
	    	},
	    	{
	    		data: null,
	    		width: '10em',
				className: 'text-center',
                render: function (data) {
                	let btnContainer = $('<section>');
                	
                	let viewLink = $('<a>', {href: '#'}).appendTo(btnContainer);
                	$('<button>', {
                		type: 'button',
                		class: 'btn btn-outline-info btn-sm m-1',
                		text: 'View'
                	}).appendTo(viewLink);
                	
                	let editLink = $('<a>', {href: '#'}).appendTo(btnContainer);
					$('<button>', {
						type: 'button',
						class: 'btn btn-outline-warning btn-sm m-1',
						text: 'Edit'
					}).appendTo(editLink);

					return btnContainer.prop('outerHTML');
                }
	    	}
	    ]
	});
	
	function filterColumn(i, val) {
		$('#appointmentList').DataTable().column(i).search(val, true, true).draw();
	}
});
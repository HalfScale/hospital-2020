$(function() {
	$('#doctorsList').DataTable({
		initComplete: function() {
			var input = $('.dataTables_filter input[type=search]').unbind(),
            self = this.api(),
            $searchButton = $('<button>').addClass('mr-3 ml-3 btn btn-primary')
                       .text('Search')
                       .click(function() {
                		  if(selectFilter.val().trim() !== '') {
                			  filterColumn(1, '^' + selectFilter.val());
                		  }else {
                			  filterColumn(1,'');
                		  }
                		  if(doctorNameInputFilter.val().trim() !== '') {
                			  filterColumn(0, doctorNameInputFilter.val());
                		  }else {
                			  filterColumn(0,'');
                		  }
                       }),
                       
            $clearButton = $('<button>').addClass('btn btn-primary')
                       .text('Clear')
                       .click(function() {
                    	   doctorNameInputFilter.val('');
                    	   selectFilter.val('');
                       })
                       
           input.parent().remove();
           const dataTablesFilter = $('.dataTables_filter').append($searchButton, $clearButton);
			
           const doctorNameFilter = $('<label>').addClass('mr-2').text('Doctor Name:');
           const doctorNameInputFilter = $('<input>').addClass('nameFilter form-control form-control-sm').attr('type', 'text').appendTo(doctorNameFilter);
           doctorNameFilter.prependTo(dataTablesFilter);
           
		   const specializationFilter = $('<label>').addClass('mr-2').text('Specialization:');
		   const selectFilter = $('<select>').addClass('statusFilter form-control form-control-sm').appendTo(specializationFilter);
		   
		   ['', 'Internal Medicine', 'Pediatrician', 'Surgeon', 'Obstetrician/Gynecologist', 'Cardiologist', 'Gastroenterologist', 'Neurologist'
		   ].forEach((status) => $('<option>').text(status).val(status).appendTo(selectFilter));
		   specializationFilter.prependTo(dataTablesFilter);
		},
		ajax: {
			url: 'api/users/doctors',
			dataSrc: 'doctors'
		},
		columns: [
			{
				data: 'user.userDetail',
				render: function(data) {
					return data.firstName + ' ' + data.lastName
				}
			},
			{data: 'doctorCode.specialization'},
			{data: 'doctorCode.description'},
			{
				data: null,
				width: '10em',
				className: 'text-center',
				render: function(data) {
					console.log('data', data);
					let btnContainer = $('<section>');
					
					let viewLink = $('<a>', {href: 'doctor/details/' + data.user.id}).appendTo(btnContainer);
                	$('<button>', {
                		type: 'button',
                		class: 'btn btn-outline-info btn-sm m-1',
                		text: 'View'
                	}).appendTo(viewLink);
                	
                	if (data.loggedUserType === 1) {
                		let editLink = $('<a>', {href: 'doctor/edit/' + data.user.id}).appendTo(btnContainer);
    					$('<button>', {
    						type: 'button',
    						class: 'btn btn-outline-warning btn-sm m-1',
    						text: 'Edit'
    					}).appendTo(editLink);
					}
    					
    				return btnContainer.prop('outerHTML');
				}
			}
		]
	});
	
	function filterColumn(i, val) {
		$('#doctorsList').DataTable().column(i).search(val, true, true).draw();
	}
});
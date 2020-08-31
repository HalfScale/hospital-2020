$(function () {
	
	let unavailableDates = [];
	const load = sysLoad().appendTo('body');
	
	const startDate = $('#appointment-start-date');
	const startDateStatus = $('.appointment-start-date-status');
	
	const endDate = $('#appointment-end-date');
	const endDateStatus = $('.appointment-end-date-status');
	
	$.getJSON($g.root_path + '/api/appointment_details_raw').done(function (result) {
		result.forEach(a => unavailableDates.push(a.appointmentStartDate));
		
		startDate.datepicker({
			dateFormat: 'mm/dd/yy',
			minDate: 0,
			beforeShowDay: unavailable
		});
		
		endDate.datepicker({
			dateFormat: 'mm/dd/yy',
			minDate: 0,
			beforeShowDay: unavailable
		});
		
		load.remove();
	});
	
	const address = $('#address');
	const addressStatus = $('.address-status');
	
	let time = [
		'01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00',
		'08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00',
		'15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00',
		'22:00', '23:00'
	];
	
	const startTime = $('#appointment-start-time');
	const startTimeStatus = $('.appointment-start-time-status');
	
	const endTime = $('#appointment-end-time');
	const endTimeStatus = $('.appointment-end-time-status');
	
	let allowed = [];
	
	$.getJSON($g.root_path + '/api/appointment_details/time').done(function(result) {
		let filtered = time.filter(t => { return result.indexOf(t) == -1});
		
		//only allow the time that is greater or equal to current time
		const today = new Date();
		
		let allowedTime = filtered.filter(at => {
			const todayHr = today.getHours();
			
			const split = at.split(':');
			const hour = Number(split[0]);
			
			if(hour > todayHr){
				console.log('hours', hour, 'todayHr', todayHr);
			}
			
			return hour >= todayHr;
		});
		
		allowed = allowedTime;
		
		console.log('allowerd', allowed);
		
		startTime.datetimepicker({
			datepicker: false,
			format: 'H:i',
			mask: true,
			allowTimes: allowed,
			value: allowed[0],
			onChangeDateTime: function(current, elem) {
				let validTime = resetTime(allowed);
				console.log('validTime', validTime);
				
				if (validTime.indexOf(elem.val()) === -1) {
					this.setOptions({
						value: validTime[0]
					});
				}
			}
		});
		
		endTime.datetimepicker({
			datepicker: false,
			format: 'H:i',
			mask: true,
			allowTimes: allowed,
			value: allowed[0],
			onChangeDateTime: function(current, elem) {
				let validTime = resetTime(allowed);
				console.log('validTime', validTime);
				
				if (validTime.indexOf(elem.val()) === -1) {
					this.setOptions({
						value: validTime[0]
					});
				}
			}
		});
		
	});
	
	const appointmentReason = $('#reason-for-appointment');
	const appointmentReasonStatus = $('.reason-for-appointment-status');
	
	$('.saveBtn').on('click', function() {
		
		let validationElem = {
			'address': false,
			'appointment-start-date': false,
			'appointment-end-date': false,
			'reason-for-appointment': false
		}
		
		if (address.length > 0 && address.val() === '') {
			setStatus(addressStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(address).addClass('is-invalid');
		}else {
			setStatus(addressStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(address).addClass('is-valid');
			validationElem['address'] = true;
		}
		
		if (startDate.val() === '') {
			setStatus(startDateStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(startDate).addClass('is-invalid');
		}else if (!isDateValid(startDate.val())) {
			setStatus(startDateStatus, 'invalid-feedback').text('Invalid Date, Required Field!');
			removeFieldStatus(startDate).addClass('is-invalid');
		}else {
			setStatus(startDateStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(startDate).addClass('is-valid');
			validationElem['appointment-start-date'] = true;
		}
		
		if (endDate.val() === '') {
			setStatus(endDateStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(endDate).addClass('is-invalid');
		}else if (!isDateValid(endDate.val())) {
			setStatus(endDateStatus, 'invalid-feedback').text('Invalid Date, Required Field!');
			removeFieldStatus(endDate).addClass('is-invalid');
		}else {
			setStatus(endDateStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(endDate).addClass('is-valid');
			validationElem['appointment-end-date'] = true;
		}
		
		if (appointmentReason.val() === '') {
			setStatus(appointmentReasonStatus, 'invalid-feedback').text('Required Field!');
			removeFieldStatus(appointmentReason).addClass('is-invalid');
		}else {
			setStatus(appointmentReasonStatus, 'valid-feedback').text('Looks good!');
			removeFieldStatus(appointmentReason).addClass('is-valid');
			validationElem['reason-for-appointment'] = true;
		}
		
		let validateFirst = null;
		let isValid = Object.keys(validationElem).every(function(key) {
			if (validationElem[key]) {
				return true;
			}else {
				validateFirst = key;
			}
		});
		
		if (isValid) {
			$('form').find('.dummy-submit').click();
		}else {
			window.location.href = '#' + validateFirst;
		}
	});
	
	function isDateValid(date) {
		const pattern = /^\d{2}\/\d{2}\/\d{4}$/;
		
		if (date.match(pattern) != null) {
			try {
				$.datepicker.parseDate( "mm/dd/yy", date);
				return true;
			}catch(error) {
				return false
			}
		}
		
		return false;
	}
	
	//For checking unavailable dates
	function unavailable(date) {
	  let string = $.datepicker.formatDate('yy-mm-dd', date);
	  console.log('string', string);
	  return [unavailableDates.indexOf(string) == -1];
    }
	
	function setStatus(elem, status) {
		return elem.removeClass('valid-feedback invalid-feedback').addClass(status);
	}
	
	function removeFieldStatus(elem) {
		return elem.removeClass('is-valid is-invalid');
	}
	
	function unavailable(date) {
	  let string = $.datepicker.formatDate('yy-mm-dd', date);
	  console.log('string', string);
	  return [ unavailableDates.indexOf(string) == -1 ];
    }
	
	//this functions resets the value of the allowed time
	//if user keeps changing the time
	function resetTime(arrTime) {
		const today = new Date();
		
		return arrTime.filter(time => {
			const todayHr = today.getHours();
			
			const split = time.split(':');
			const hour = Number(split[0]);
			
			return hour >= todayHr;
		});
	}
});
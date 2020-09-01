$(function () {
	const load = sysLoad().appendTo('body');
	
	const startDate = $('#appointment-start-date');
	const startDateStatus = $('.appointment-start-date-status');
	
	const endDate = $('#appointment-end-date');
	const endDateStatus = $('.appointment-end-date-status');
	
	const unavailableDates = [];
	$.getJSON($g.root_path + '/api/appointment_details_raw').done(function(result) {
		result.forEach(a => unavailableDates.push(a.appointmentStartDate));
		
		startDate.datepicker({
			dateFormat: 'mm/dd/yy',
			minDate: 0,
			beforeShowDay: unavailable,
		    changeMonth: true,
		    changeYear: true
		});
		
		endDate.datepicker({
			dateFormat: 'mm/dd/yy',
			minDate: 0,
			beforeShowDay: unavailable,
		    changeMonth: true,
		    changeYear: true
		});
		
		load.remove();
	});
	
	let timeList = [
		'01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00',
		'08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00',
		'15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00',
		'22:00', '23:00'
	];
	
	let allowedTime = [];
	
	const startTime = $('#appointment-start-time');
	const endTime = $('#appointment-end-time');
	
	$.getJSON($g.root_path + '/api/appointment_details/time').done(function(result) {
		console.log('/api/appointment_details/time', result);
		
		const filtered = timeList.filter(t => { return result.indexOf(t) == -1; });
		console.log('filtered', filtered);
		
		//only allow the time that is greater or equal to current time
		const today = new Date();
		
		allowedTime = filtered.filter(at => {
			const todayHr = today.getHours();
			
			const split = at.split(':');
			const hour = Number(split[0]);
			
			return hour >= todayHr;
		});
		
		console.log('allowedTime', allowedTime);
		load.remove();
		
		const startTimeDefault = $('#startTime').val();
		
		startTime.datetimepicker({
			datepicker: false,
			format: 'H:i',
			mask: true,
			value: startTimeDefault,
			allowTimes: allowedTime,
			onChangeDateTime: function(current, elem) {
				const validTime = resetTime(allowedTime);
				
				if (validTime.indexOf(elem.val()) === -1) {
					this.setOptions({
						value: startTimeDefault
					});
				}
			}
		});
		
		const endTimeDefault = $('#endTime').val();
		
		endTime.datetimepicker({
			datepicker: false,
			format: 'H:i',
			mask: true,
			value: startTimeDefault,
			allowTimes: allowedTime,
			onChangeDateTime: function(current, elem) {
				const validTime = resetTime(allowedTime);
				
				if (validTime.indexOf(elem.val()) === -1) {
					this.setOptions({
						value: endTimeDefault
					});
				}
			}
		});
	});
	
	const address = $('#address');
	const addressStatus = $('.address-status');
	
	const appointmentReason = $('#reason-for-appointment');
	const appointmentReasonStatus = $('.reason-for-appointment-status');
	
	$('.saveBtn').on('click', function () {
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
	
	function setStatus(elem, status) {
		return elem.removeClass('valid-feedback invalid-feedback').addClass(status);
	}
	
	function removeFieldStatus(elem) {
		return elem.removeClass('is-valid is-invalid');
	}
	
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
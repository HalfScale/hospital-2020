package hospital.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.entity.AppointmentDetail;
import hospital.entity.User;
import hospital.facade.IAuthenticationFacade;
import hospital.service.AppointmentDetailService;
import hospital.service.UserService;
import hospital.util.Util;

@RestController
@RequestMapping("/api")
public class AppointmentDetailRestController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentDetailService appointmentDetailService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@GetMapping("/appointment_details/time")
	public Set<String> getAppointmentDetailsTime() {
		Set<String> time = new HashSet<>();
		appointmentDetailService.getAppointmentDetails().forEach(ad -> {
			time.add(Util.formatDate(ad.getAppointmentStartTime(), "HH:mm"));
			time.add(Util.formatDate(ad.getAppointmentEndTime(), "HH:mm"));
		});
		return time;
	}
	
	@GetMapping("/appointment_details_raw")
	public List<AppointmentDetail> getAppointmentDetailsRaw() {
		return appointmentDetailService.getAppointmentDetails();
	}
	
	@GetMapping("/appointment_details")
	public List<Map> getAppointmentDetails() throws Exception {
		User user = authenticationFacade.getUser();
		
		if (user == null) {
			throw new Exception("Invalid user!");
		}
		
		//If user is of type admin then query all appointment details
		if(user.getUserType() == 1) {
			return appointmentDetailService.getAppointmentDetails().stream()
					.map(a -> {
						Map data = new HashMap();
						data.put("appointmentDetail", a);
						return data;
					}).collect(Collectors.toList()); 
		}
		
		//If user is of type doctor then query all appointment details that is related to this doctor
		if (user.getUserType() == 2) {
			return appointmentDetailService.getAppointmentDetailsByDoctor(user.getId()).stream()
					.map(a -> {
						Map data = new HashMap();
						data.put("appointmentDetail", a);
						return data;
					}).collect(Collectors.toList());
		}
		
		return appointmentDetailService.getAppointmentDetailsByPatient(user.getId()).stream()
				.map(a -> {
					Map data = new HashMap();
					User doctor = userService.getUser(a.getAppointment().getDoctorId());
					data.put("appointmentDetail", a);
					data.put("doctor", doctor);
					return data;
				}).collect(Collectors.toList());
	}
}

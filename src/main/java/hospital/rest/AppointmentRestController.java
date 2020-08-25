package hospital.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.service.AppointmentService;

@RestController
@RequestMapping("/api")
public class AppointmentRestController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/appointments")
	public List<Map> getAppointments() {
		return appointmentService.getAppointments().stream()
				.map(a -> {
					Map data = new HashMap();
					data.put("appointment", a)
				})
	}
}

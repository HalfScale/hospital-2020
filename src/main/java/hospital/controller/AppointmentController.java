package hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	
	@GetMapping
	public String appointmentList() {
		return "appointments";
	}

	@GetMapping("/add")
	public String addApointment() {
		return "appointment_add_edit";
	}
	
	@GetMapping("/confirm")
	public String confirmAppointment() {
		return "appointment_confirm";
	}
	
	
}

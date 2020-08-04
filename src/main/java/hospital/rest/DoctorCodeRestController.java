package hospital.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.entity.DoctorCode;
import hospital.service.DoctorCodeService;

@RestController
@RequestMapping("/api")
public class DoctorCodeRestController {

	@Autowired
	private DoctorCodeService doctorCodeService;
	
	@GetMapping("/doctor_code/{code}")
	public DoctorCode getDoctorCode(@PathVariable int code) {
		return doctorCodeService.getDoctorCode(code);
	}
}

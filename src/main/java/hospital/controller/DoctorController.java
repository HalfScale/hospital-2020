package hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hospital.entity.DoctorCode;
import hospital.entity.User;
import hospital.service.DoctorCodeService;
import hospital.service.UserService;

@Controller
public class DoctorController {
	
	@Autowired
	private UserService userSerivce;
	
	@Autowired
	private DoctorCodeService doctorCodeService;

	@GetMapping("/doctors")
	public String listDoctors() {
		return "doctors";
	}
	
	@GetMapping("/doctor/details/{id}")
	public String doctorDetails(@PathVariable int id, Model model) {
		User user = userSerivce.getUser(id);
		DoctorCode doctorCode = doctorCodeService.getDoctorCode(user.getUserDetail().getDoctorCodeId());
		
		model.addAttribute("doctorUser", user);
		model.addAttribute("doctor", doctorCode);
		return "doctors_profile";
	}
	
	@GetMapping("/doctor/edit/{id}")
	public String editDoctor(@PathVariable int id, Model model) {
		User user = userSerivce.getUser(id);
		DoctorCode doctorCode = doctorCodeService.getDoctorCode(user.getUserDetail().getDoctorCodeId());
		
		model.addAttribute("doctorUser", user);
		model.addAttribute("doctor", doctorCode);
		return "doctors_edit";
	}
	
}

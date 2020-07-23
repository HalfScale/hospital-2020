package hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordController {
	
	@GetMapping("/password/reset/")
	public String passwordReset() {
		return "password_reset";
	}
	
	@GetMapping("/password/update")
	public String passwordUpdate() {
		return "password_update";
	}
}

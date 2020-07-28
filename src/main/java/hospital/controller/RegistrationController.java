package hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hospital.entity.User;
import hospital.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String viewRegistration() {
		return "registration";
	}
	
	@GetMapping("/confirm/{token}")
	public ModelAndView viewRegistrationConfirmation(@PathVariable String token) {
		System.out.println("token: " + token);
		ModelAndView modelAndView = new ModelAndView("confirm_registration");
		
		User user = userService.findByToken(token);
		
		if(user != null && !user.isEnabled() && !user.isConfirmed()) {
			user.setConfirmed(true);
			user.setEnabled(true);
			modelAndView.addObject("message", "Registration has been successfully confirmed! <b>Please login.<b>");
			modelAndView.addObject("status", "success");
			userService.saveUser(user);
		}else {
			modelAndView.addObject("message", "Invalid token!");
			modelAndView.addObject("status", "danger");
		}
		
		return modelAndView;
	}
}

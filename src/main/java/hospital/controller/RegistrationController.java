package hospital.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
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
		
		if(isValidToken(user)) {
			user.setConfirmed(true);
			user.setEnabled(true);
			user.setModified(LocalDateTime.now());
			modelAndView.addObject("message", "Registration has been successfully confirmed! <b>Please login.<b>");
			modelAndView.addObject("status", "success");
			userService.saveUser(user);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token is invalid");
		}
		
		return modelAndView;
	}
	
	private boolean isValidToken(User user) {
		//If token exceeds 24 hrs
		int result = LocalDateTime.now().compareTo(user.getCreated().plusDays(1));
		
		return user != null &&
				!user.isEnabled() &&
				!user.isConfirmed() &&
				!user.isDeleted() &&
				result == -1;
	}
}

package hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import hospital.entity.User;
import hospital.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users/login")
	public String showLogin() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (!(auth instanceof AnonymousAuthenticationToken)) {
	        return "redirect:/";
	    }
//	    
		return "login";
	}
	
	@GetMapping("/access_denied")
	public String showAccessDeniedPage() {
		return "access_denied";
	}
	
	
}

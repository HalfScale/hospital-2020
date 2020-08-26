package hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hospital.facade.IAuthenticationFacade;
import hospital.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@GetMapping("/users/login")
	public String showLogin(@RequestParam(required=false) String logout) {
		
		System.out.println("logout? " + logout);
		
		if (logout != null) {
			authenticationFacade.setEmail(null);
		}
		
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

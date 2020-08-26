package hospital.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hospital.entity.User;
import hospital.facade.IAuthenticationFacade;
import hospital.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@GetMapping
	public ModelAndView viewHome(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("home");
		
		Authentication auth = authenticationFacade.getAuthentication();
        
        if (!auth.getName().equals("anonymousUser")) {
        	User user = null;
        	String email = authenticationFacade.getEmail();
        	
        	//initally email is null
        	if (email != null) {
        		User checkUser = authenticationFacade.getUser();
				user = userService.getUser(email);
			}else {
				user = authenticationFacade.getUser();
			}
        	request.getSession().setAttribute("user", user);
		}
        
		return model;
	}
}

package hospital.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hospital.entity.User;
import hospital.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ModelAndView viewHome(HttpServletRequest request) {
        logger.info("Info log");
		ModelAndView model = new ModelAndView("home");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        
        if (!auth.getName().equals("anonymousUser")) {
        	User user = userService.getUser(auth.getName());
        	request.getSession().setAttribute("user", user);
		}
        
		return model;
	}
}

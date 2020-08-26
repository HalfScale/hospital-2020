package hospital.facade;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import hospital.entity.User;
import hospital.service.UserService;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
	
	private static final Logger logger = LogManager.getLogger(AuthenticationFacade.class);
	
	@Autowired
	private UserService userService;
	
	private String email;
	
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public User getUser() {
		Authentication authentication = this.getAuthentication();
		
		//We set a new email after a user updated his email.
		this.email = this.email != null ? this.email : authentication.getName();
		
		//Check if user is authenticated
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			System.out.println("email: " + this.email);
		    return userService.getUser(this.email);
		}
		
		return null;
	}
	
	@Override
	public SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}
	
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}
}

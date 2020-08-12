package hospital.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import hospital.entity.User;
import hospital.service.UserService;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public User getUser() {
		Authentication authentication = this.getAuthentication();
		//Check if user is authenticated
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return userService.getUser(currentUserName);
		}
		
		return null;
	}

}

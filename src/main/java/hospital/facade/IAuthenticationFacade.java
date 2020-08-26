package hospital.facade;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import hospital.entity.User;

public interface IAuthenticationFacade {
	Authentication getAuthentication();
	User getUser();
	SecurityContext getSecurityContext();
	String getEmail();
	void setEmail(String email);
}

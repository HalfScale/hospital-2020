package hospital.facade;

import org.springframework.security.core.Authentication;

import hospital.entity.User;

public interface IAuthenticationFacade {
	Authentication getAuthentication();
	User getUser();
}

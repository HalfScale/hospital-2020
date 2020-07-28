package hospital.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import hospital.entity.Authorities;
import hospital.entity.MyResponseObject;
import hospital.entity.User;
import hospital.event.OnRegistrationSuccessEvent;
import hospital.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/users/email/{email:.+}/")
	public User getUser(@PathVariable String email) {
		User user = userService.findEmail(email);
		return user != null ? user : new User();
	}
	
	@PostMapping("/users")
	public MyResponseObject addUser(@RequestBody User user, WebRequest request) {
		user.setId(0);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.getUserDetail().setUser(user);
		user.getUserDetail().setCreated(LocalDateTime.now());
		user.getUserDetail().setModified(LocalDateTime.now());
		user.setRegistrationToken(generateToken());
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		
		Set<Authorities> authorities = new HashSet<>();
		if (user.getUserType() == 2) {
			authorities.add(new Authorities("ROLE_DOCTOR", user));
		}else {
			authorities.add(new Authorities("ROLE_PATIENT", user));
		}
		user.setAuthorities(authorities);
		
		userService.saveUser(user);
		
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, request.getLocale(),appUrl));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MyResponseObject response = new MyResponseObject();
		response.setCode("202");
		response.setResponse("Saving successful! Please Check your email to verify.");
		response.setData(user);
		return response;
	}
	
	public String generateToken() {
		StringBuilder token = new StringBuilder();
		
		int count = userService.getUsers().size() + 1;
		String date  = LocalDate.now().toString();
		String id = UUID.randomUUID().toString();
		
		token.append(count);
		token.append(date);
		token.append(id);
		
		return token.toString();
	}
}

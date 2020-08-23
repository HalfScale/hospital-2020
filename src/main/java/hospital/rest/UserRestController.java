package hospital.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hospital.entity.Authorities;
import hospital.entity.DoctorCode;
import hospital.entity.MyResponseObject;
import hospital.entity.User;
import hospital.event.OnRegistrationSuccessEvent;
import hospital.facade.IAuthenticationFacade;
import hospital.service.DoctorCodeService;
import hospital.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	private static final Logger logger = LogManager.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorCodeService doctorCodeService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
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
	
	@GetMapping("/users/doctors")
	public Map getUserByType() {
		Map data = new HashMap();
		User user = authenticationFacade.getUser();
		
		//We need to get the logged in user type
		int userType = user != null ? user.getUserType() : -1;
		
		//We get all the doctors
		List<Map> doctors = userService.findByType(2).stream().map(u -> {
			//We redefine the data structure to suffice the front end needs
			Map doctorMap = new HashMap();
			DoctorCode doctorCode = doctorCodeService.getDoctorCode(u.getUserDetail().getDoctorCodeId());
			doctorMap.put("user", u);
			doctorMap.put("doctorCode", doctorCode);
			doctorMap.put("loggedUserType", userType);
			
			return doctorMap;
		}).collect(Collectors.toList());
		
		data.put("doctors", doctors);
		
		return data;
	} 
}

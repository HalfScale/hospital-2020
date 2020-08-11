package hospital.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hospital.entity.Authorities;
import hospital.entity.User;
import hospital.entity.UserDetail;
import hospital.event.OnRegistrationSuccessEvent;
import hospital.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	public String viewRegistration() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (!(auth instanceof AnonymousAuthenticationToken)) {
	        return "redirect:/";
	    }
	    
		return "registration";
	}
	
	@PostMapping
	public String registerUser(
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="email") String email,
			@RequestParam(value="gender") String gender,
			@RequestParam(value="mobileNo") String mobileNo,
			@RequestParam(value="password") String password,
			@RequestParam(value="doctorCodeId", required = false) String doctorCodeId,
			RedirectAttributes attr,
			WebRequest request
			) {
		System.out.println("Register " + firstName + " " + lastName);
		System.out.println("doctorCodeId = " + doctorCodeId);
		
		User user = new User();
		user.setUserType(3); // 3 for patient
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password.trim()));
		user.setRegistrationToken(generateToken());
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		
		UserDetail userDetail = new UserDetail();
		userDetail.setFirstName(firstName);
		userDetail.setLastName(lastName);
		userDetail.setGender(Integer.parseInt(gender));
		userDetail.setMobileNo(Long.parseLong(mobileNo));
		userDetail.setCreated(LocalDateTime.now());
		userDetail.setModified(LocalDateTime.now());
		
		if (!doctorCodeId.trim().isEmpty()) {
			userDetail.setDoctorCodeId(Integer.parseInt(doctorCodeId));
			user.setUserType(2); // 2 for doctor
		}
		
		userDetail.setUser(user);
		user.setUserDetail(userDetail);
		
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
		
		attr.addFlashAttribute("message", "Registration Successful! Please Check your email to verify.");
		
		return "redirect:/users/login";
	}
	
	@GetMapping("/confirm/{token}")
	public ModelAndView viewRegistrationConfirmation(@PathVariable String token) {
		System.out.println("token: " + token);
		ModelAndView modelAndView = new ModelAndView("confirm_registration");
		
		User user = userService.findByToken(token);
		
		if(isValidToken(user)) {
			user.setConfirmed(true);
			user.setEnabled(true);
			user.setModified(LocalDateTime.now());
			modelAndView.addObject("message", "Registration has been successfully confirmed! <b>Please login.<b>");
			modelAndView.addObject("status", "success");
			userService.saveUser(user);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token is invalid");
		}
		
		return modelAndView;
	}
	
	private String generateToken() {
		StringBuilder token = new StringBuilder();
		
		int count = userService.getUsers().size() + 1;
		String date  = LocalDate.now().toString();
		String id = UUID.randomUUID().toString();
		
		token.append(count);
		token.append(date);
		token.append(id);
		
		return token.toString();
	}
	
	private boolean isValidToken(User user) {
		//If token exceeds 24 hrs
		int result = LocalDateTime.now().compareTo(user.getCreated().plusDays(1));
		
		return user != null &&
				!user.isEnabled() &&
				!user.isConfirmed() &&
				!user.isDeleted() &&
				result == -1;
	}
}

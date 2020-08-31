package hospital.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hospital.entity.DoctorCode;
import hospital.entity.User;
import hospital.entity.UserDetail;
import hospital.facade.IAuthenticationFacade;
import hospital.service.DoctorCodeService;
import hospital.service.UserService;
import hospital.util.Util;

@Controller
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DoctorCodeService doctorCodeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/users/info")
	public String viewUser(Model model) {
		User user = authenticationFacade.getUser();
		
		if (user == null) {
			return "redirect:/";
		}
		
		if (user.getUserType() == 2) {
			DoctorCode doctorCode = doctorCodeService.getDoctorCode(user.getUserDetail().getDoctorCodeId());
			model.addAttribute("doctor", doctorCode);
		}
		
		LocalDate birthDate = user.getUserDetail().getBirthDate();
		if (birthDate != null) {
			model.addAttribute("birthDate", Util.formatDate(birthDate, "MM/dd/yyyy"));
		}
		
		return "profile";
	}
	
	@GetMapping("/users/info/{id}")
	public String viewUserById(@PathVariable int id, Model model) {
		User authUser = authenticationFacade.getUser();
		
		if (authUser == null) {
			return "redirect:/";
		}
		
		User user = userService.getUser(id);
		model.addAttribute("userInfo", user);
		
		LocalDate birthDate = user.getUserDetail().getBirthDate();
		if (birthDate != null) {
			model.addAttribute("birthDate", Util.formatDate(birthDate, "MM/dd/yyyy"));
		}
		
		if (user.getUserType() == 2) {
			DoctorCode doctorCode = doctorCodeService.getDoctorCode(user.getUserDetail().getDoctorCodeId());
			model.addAttribute("doctor", doctorCode);
		}
		
		return "profile_view";
	}
	
	@GetMapping("/users/edit/info")
	public String editUser(Model model) {
		User user = authenticationFacade.getUser();
		
		if (user == null) {
			return "redirect:/";
		}
		
		if (user.getUserType() == 2) {
			DoctorCode doctorCode = doctorCodeService.getDoctorCode(user.getUserDetail().getDoctorCodeId());
			model.addAttribute("doctor", doctorCode);
		}
		
		return "profile_edit";
	}
	
	@PostMapping("/processUserUpdate")
	public String processUserUpdate(
			@RequestParam Map<String, String> params, 
			Model model, 
			RedirectAttributes redirectAttr,
			HttpServletRequest request) {
		User user = authenticationFacade.getUser();
		
		logger.info("user update params: " + params);
		logger.info("user " + user.getUserDetail().getFirstName());
		
		String firstName = params.get("firstName");
		String lastName = params.get("lastName");
		String email = params.get("email");
		String mobileNo = params.get("mobileNo");
		String password = !params.get("password").isEmpty() ? passwordEncoder.encode(params.get("password")) : user.getPassword();
		String gender = params.get("gender");
		String address = !params.get("address").isEmpty() ? params.get("address") : null;
		String birthDate = !params.get("birthDate").isEmpty() ? params.get("birthDate") : null;
		
		UserDetail userDetail = user.getUserDetail();
		userDetail.setFirstName(firstName);
		userDetail.setLastName(lastName);
		user.setEmail(email);
		userDetail.setMobileNo(Long.valueOf(mobileNo));
		user.setPassword(password);
		userDetail.setGender(Integer.parseInt(gender));
		userDetail.setAddress(address);
		userDetail.setBirthDate(birthDate != null ? LocalDate.parse(birthDate) : null);
		user.setUserDetail(userDetail);
		
		//User of type hospital
		if (user.getUserType() == 2) {
			//This is doctor_code column in user details
			String specialization = params.get("specialization");
			String noOfYearsExperience = params.get("noOfYearsInService");
			String education = params.get("education");
			String schedule = params.get("schedule");
			String expertise = params.get("expertise");
			
			userDetail.setDoctorCodeId(Integer.parseInt(specialization));
			userDetail.setNoOfYearsExperience(Integer.parseInt(noOfYearsExperience));
			userDetail.setEducation(education);
			userDetail.setSchedule(schedule);
			userDetail.setExpertise(expertise);
		}
		
		user.setModified(LocalDateTime.now());
		userDetail.setModified(LocalDateTime.now());
		
		userService.saveUser(user);
		
		redirectAttr.addFlashAttribute("message", "Successfully Updated User Profile!");
//		
		Authentication auth = authenticationFacade.getAuthentication();
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
//		
		SecurityContext securityContext = authenticationFacade.getSecurityContext();
	    securityContext.setAuthentication(newAuth);
	    
	    authenticationFacade.setEmail(user.getEmail());
	    
//		
		HttpSession session = request.getSession(true);
	    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
	    session.setAttribute("user", user);
		
		return "redirect:/users/info";
	}
}

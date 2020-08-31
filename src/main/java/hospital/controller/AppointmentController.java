package hospital.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hospital.entity.Appointment;
import hospital.entity.AppointmentDetail;
import hospital.entity.AppointmentDetailHistory;
import hospital.entity.AppointmentHistory;
import hospital.entity.User;
import hospital.facade.IAuthenticationFacade;
import hospital.service.AppointmentHistoryService;
import hospital.service.AppointmentService;
import hospital.service.UserService;
import hospital.util.Util;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	private static final Logger logger = LogManager.getLogger(AppointmentController.class);
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private AppointmentHistoryService appointmentHistoryService;
	
	@Autowired IAuthenticationFacade authFacade;
	
	@Autowired 
	private	UserService userService;
	
	@GetMapping
	public String appointmentList() {
		return "appointments";
	}

	@GetMapping("/add")
	public String addApointment() {
		User user = authFacade.getUser();
		
		if (user.getUserType() == 2) {
			return "redirect:/appointments";
		}
		
		return "appointment_add";
	}
	
	@GetMapping("/details/{id}")
	public String viewAppointmentDetails(@PathVariable int id, Model model) {
		User user = authFacade.getUser();
		Appointment appointment = appointmentService.getAppointment(id);
		
		Map attributes = new HashMap();
		User patient = userService.getUser(appointment.getPatientId());
		attributes.put("firstName", patient.getUserDetail().getFirstName());
		attributes.put("lastName", patient.getUserDetail().getLastName());
		attributes.put("gender", patient.getUserDetail().getGender());
		attributes.put("mobileNo", patient.getUserDetail().getMobileNo());
		attributes.put("email", patient.getEmail());
		
		String startDate = Util.formatDate(appointment.getAppointmentDetail().getAppointmentStartDate(), "MM/dd/yyyy");
		String endDate = Util.formatDate(appointment.getAppointmentDetail().getAppointmentEndDate(), "MM/dd/yyyy");
		
		attributes.put("appointmentId", appointment.getId());
		attributes.put("status", appointment.getAppointmentStatus());
		attributes.put("address", appointment.getAppointmentDetail().getAddress());
		attributes.put("firstTime", appointment.getAppointmentDetail().isFirstTime());
		attributes.put("startDate", startDate);
		attributes.put("startTime", appointment.getAppointmentDetail().getAppointmentStartTime());
		attributes.put("endDate", endDate);
		attributes.put("endTime", appointment.getAppointmentDetail().getAppointmentEndTime());
		attributes.put("appointmentReason", appointment.getAppointmentDetail().getAppointmentReason());
		attributes.put("cancelReason", appointment.getAppointmentDetail().getCancelReason());
		
		model.addAllAttributes(attributes);
		
		return "appointment_details";
	}
	
	@GetMapping("/edit/{id}")
	public String editAppointment(@PathVariable int id, Model model) {
		Appointment appointment = appointmentService.getAppointment(id);
		model.addAttribute("appointment", appointment);
		model.addAttribute("something", "something");
		return "appointment_edit";
	}
	
	@GetMapping("/add/complete")
	public String appointmentComplete() {
		return "appointment_complete";
	}
	
	@GetMapping("/add/confirm")
	public String confirmAppointment(@RequestParam Map<String, String> params, Model model) {
		
		User user = authFacade.getUser();
		
		if (user.getUserType() == 1) {
			return "redirect:/appointments";
		}
		
		String startDate = params.get("appointmentStartDate");
		String startTime = params.get("appointmentStartTime");
		String endDate = params.get("appointmentEndDate");
		String endTime = params.get("appointmentEndTime");
		
		params.put("startDate", startDate);
		params.put("startTime", startTime);
		params.put("endDate", endDate);
		params.put("endTime", endTime);
		
		model.addAllAttributes(params);
		return "appointment_confirm";
	}
	
	@PostMapping("/approve")
	public String approveAppointment(HttpServletRequest request, RedirectAttributes redirAttr) {
		String id = request.getParameter("appointmentId");
		
		Appointment appointment = appointmentService.getAppointment(Integer.parseInt(id));
		
		appointment.setAppointmentStatus(Appointment.Status.APPROVED);
		appointment.setModified(LocalDateTime.now());
		
		AppointmentHistory appointmentHistory = new AppointmentHistory();
		appointmentHistory.setAppointmentStatus(Appointment.Status.APPROVED);
		appointmentHistory.setAppointment(appointment);
		appointmentHistory.setPatientId(appointment.getPatientId());
		appointmentHistory.setDoctorId(appointment.getDoctorId());
		appointmentHistory.setCreated(appointment.getCreated());
		appointmentHistory.setModified(LocalDateTime.now());
		
		appointmentHistoryService.saveAppointmentHistory(appointmentHistory);
		
		appointmentService.saveAppointment(appointment);
		redirAttr.addFlashAttribute("message", "Appointment Successfully Approved!");
		
		return "redirect:/appointments/details/" + appointment.getId(); 
		
	}
	
	@PostMapping("/cancel")
	public String cancelAppointment(HttpServletRequest request, RedirectAttributes redirAttr) {
		User user = authFacade.getUser();
		
		String id = request.getParameter("appointmentId");
		String cancelReason = request.getParameter("cancelReason");
		
		Appointment appointment = appointmentService.getAppointment(Integer.parseInt(id));
		//create new appointmenthistory
		
		AppointmentHistory appointmentHistory = new AppointmentHistory();
		
		if (user.getUserType() == 3) {
			appointment.setAppointmentStatus(Appointment.Status.CANCELLED_PATIENT);
			appointmentHistory.setAppointmentStatus(Appointment.Status.CANCELLED_PATIENT);
		}
		
		if (user.getUserType() == 2) {
			appointment.setAppointmentStatus(Appointment.Status.CANCELLED_HOSPITAL);
			appointmentHistory.setAppointmentStatus(Appointment.Status.CANCELLED_HOSPITAL);
		}
		
		appointment.setModified(LocalDateTime.now());
		
		appointmentHistory.setAppointment(appointment);
		appointmentHistory.setPatientId(appointment.getPatientId());
		appointmentHistory.setDoctorId(appointment.getDoctorId());
		appointmentHistory.setCreated(appointment.getCreated());
		appointmentHistory.setModified(LocalDateTime.now());
		
		appointmentHistoryService.saveAppointmentHistory(appointmentHistory);
//		
		AppointmentDetail appointmentDetail = appointment.getAppointmentDetail();
		
		appointmentDetail.setCancelReason(cancelReason);
		appointmentDetail.setModified(LocalDateTime.now());
//		
		List<AppointmentDetailHistory> appointmentDetailHistories = appointmentDetail.getAppointmentDetailHistories();
		AppointmentDetailHistory appointmentDetailHistory = appointmentDetailHistories.get(appointmentDetailHistories.size() - 1);
//		
		//creating new detail history
		appointmentDetailHistory.setId(0);
		appointmentDetailHistory.setCancelReason(cancelReason);
		appointmentDetailHistory.setModified(LocalDateTime.now());
		appointmentDetailHistories.add(appointmentDetailHistory);
		
		appointmentService.saveAppointment(appointment);
		
		redirAttr.addFlashAttribute("message", "Appointment Successfully cancelled!");
		
		return "redirect:/appointments/details/" + appointment.getId();
	}
	
	@PostMapping("/processAppointment")
	public String processAppointment(
			@RequestParam Map<String, String> params, 
			RedirectAttributes redirAttr) {
		
		//Note that we use one process url for both add and edit
		//This is for adding appointment
		
		int appointmentId = params.get("appointment") != null ? Integer.parseInt(params.get("appointment")) : -1;
		
		String patientId = params.get("patientId");
		String doctorId = params.get("doctorId");
		String firstName = params.get("firstName");
		String lastName = params.get("lastName");
		String address = params.get("address");
		String gender = params.get("gender");
		String startDate = params.get("startDate");
		String startTime = params.get("startTime");
		String endDate = params.get("endDate");
		String endTime = params.get("endTime");
		String mobileNo = params.get("mobileNo");
		String email = params.get("email");
		boolean firstTime = params.get("hospitalVisit").equals("Yes");
		String appointmentReason = params.get("appointmentReason");
		
		if (appointmentId != -1) {
			Appointment appointmentUpdate = appointmentService.getAppointment(appointmentId);
			AppointmentDetail detailUpdate = appointmentUpdate.getAppointmentDetail();
			List<AppointmentDetailHistory> detailHistoryUpdates = detailUpdate.getAppointmentDetailHistories();
			
			detailUpdate.setAddress(address);
			detailUpdate.setFirstTime(firstTime);
			detailUpdate.setAppointmentStartDate(LocalDate.parse(startDate));
			detailUpdate.setAppointmentStartTime(LocalTime.parse(startTime));
			detailUpdate.setAppointmentEndDate(LocalDate.parse(endDate));
			detailUpdate.setAppointmentEndTime(LocalTime.parse(endTime));
			detailUpdate.setAppointmentReason(appointmentReason);
			detailUpdate.setModified(LocalDateTime.now());
			
			//adding new entry
			AppointmentDetailHistory detailHistory = detailHistoryUpdates.get(0); // Just get anything just o retain the first name and etc, to make it convinient.
			detailHistory.setId(0); // sets zero since we are inserting a new one.
			detailHistory.setAddress(address);
			detailHistory.setFirstTime(firstTime);
			detailHistory.setAppointmentStartDate(LocalDate.parse(startDate));
			detailHistory.setAppointmentStartTime(LocalTime.parse(startTime));
			detailHistory.setAppointmentEndDate(LocalDate.parse(endDate));
			detailHistory.setAppointmentEndTime(LocalTime.parse(endTime));
			detailHistory.setAppointmentReason(appointmentReason);
			detailHistory.setModified(LocalDateTime.now());
			detailHistory.setCreated(LocalDateTime.now());
			
			redirAttr.addFlashAttribute("message", "Appointment Update Successful!");
			appointmentService.saveAppointment(appointmentUpdate);
			
			return "redirect:/appointments";
			
		}
			
		Appointment appointment = new Appointment();
		appointment.setPatientId(Integer.parseInt(patientId));
		appointment.setDoctorId(Integer.parseInt(doctorId));
		appointment.setAppointmentStatus(Appointment.Status.PENDING);
		appointment.setCreated(LocalDateTime.now());
		appointment.setModified(LocalDateTime.now());
		
		AppointmentHistory appointmentHistory = new AppointmentHistory();
		appointmentHistory.setAppointment(appointment);
		appointmentHistory.setPatientId(Integer.parseInt(patientId));
		appointmentHistory.setDoctorId(Integer.parseInt(doctorId));
		appointmentHistory.setAppointmentStatus(Appointment.Status.PENDING);
		appointmentHistory.setCreated(LocalDateTime.now());
		appointmentHistory.setModified(LocalDateTime.now());
		
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		appointmentDetail.setAppointment(appointment);
		appointmentDetail.setFirstName(firstName);
		appointmentDetail.setLastName(lastName);
		appointmentDetail.setAddress(address);
		appointmentDetail.setGender(Integer.parseInt(gender));
		appointmentDetail.setFirstTime(firstTime);
		appointmentDetail.setAppointmentStartDate(LocalDate.parse(startDate));
		appointmentDetail.setAppointmentStartTime(LocalTime.parse(startTime));
		appointmentDetail.setAppointmentEndDate(LocalDate.parse(endDate));
		appointmentDetail.setAppointmentEndTime(LocalTime.parse(endTime));
		appointmentDetail.setMobileNo(Long.parseLong(mobileNo));
		appointmentDetail.setEmail(email);
		appointmentDetail.setAppointmentReason(appointmentReason);
		appointmentDetail.setCreated(LocalDateTime.now());
		appointmentDetail.setModified(LocalDateTime.now());
		
		AppointmentDetailHistory appointmentDetailHistory = new AppointmentDetailHistory();
		appointmentDetailHistory.setAppointment(appointment);
		appointmentDetailHistory.setAppointmentDetail(appointmentDetail);
		appointmentDetailHistory.setFirstName(firstName);
		appointmentDetailHistory.setLastName(lastName);
		appointmentDetailHistory.setAddress(address);
		appointmentDetailHistory.setGender(Integer.parseInt(gender));
		appointmentDetailHistory.setFirstTime(firstTime);
		appointmentDetailHistory.setAppointmentStartDate(LocalDate.parse(startDate));
		appointmentDetailHistory.setAppointmentStartTime(LocalTime.parse(startTime));
		appointmentDetailHistory.setAppointmentEndDate(LocalDate.parse(endDate));
		appointmentDetailHistory.setAppointmentEndTime(LocalTime.parse(endTime));
		appointmentDetailHistory.setMobileNo(Long.parseLong(mobileNo));
		appointmentDetailHistory.setEmail(email);
		appointmentDetailHistory.setAppointmentReason(appointmentReason);
		appointmentDetailHistory.setCreated(LocalDateTime.now());
		appointmentDetailHistory.setModified(LocalDateTime.now());
		
		Set<AppointmentHistory> appointmentHistories = appointment.getAppointmentHistories() != null ? appointment.getAppointmentHistories() : new HashSet<>();
		appointmentHistories.add(appointmentHistory);
		appointment.setAppointmentHistories(appointmentHistories);
		appointment.setAppointmentDetail(appointmentDetail);
		
		appointment.setAppointmentDetailHistories(appointmentDetailHistory);
		appointmentService.saveAppointment(appointment);
		
		
		return "appointment_complete";
		
	}
	
	
}

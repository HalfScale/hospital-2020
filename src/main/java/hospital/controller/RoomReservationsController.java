package hospital.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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

import hospital.entity.HospitalRoom;
import hospital.entity.RoomReservation;
import hospital.entity.User;
import hospital.entity.UserDetail;
import hospital.facade.IAuthenticationFacade;
import hospital.service.HospitalRoomService;
import hospital.service.RoomReservationService;
import hospital.util.Util;

@Controller
@RequestMapping("/reservations")
public class RoomReservationsController {
	private static final Logger logger = LogManager.getLogger(RoomReservationsController.class);
	
	@Autowired
	private RoomReservationService roomReservationService;
	
	@Autowired
	private HospitalRoomService hospitalRoomService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@GetMapping
	public String roomResrvationList() {
		return "room_reservations";
	}
	
	@GetMapping("/add")
	public String createReservation() {
		return "room_reservations_add";
	}
	
	@PostMapping("/add/confirm") 
	public String confirmReservation(@RequestParam Map<String,String> allParams, Model model) {
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		model.addAttribute("roomCode", allParams.get("roomCode"));
		model.addAttribute("reservedDate", allParams.get("reservedDate"));
		model.addAttribute("reservedTime", allParams.get("reservedTime"));
		model.addAttribute("reservedEndDate", allParams.get("reservedEndDate"));
		model.addAttribute("reservedEndTime", allParams.get("reservedEndTime"));
		model.addAttribute("hospitalRoom", allParams.get("hospitalRoom"));
		
		String formattedReservedTime = LocalTime.parse(allParams.get("reservedTime")).format(timeFormat);
		String formattedReservedEndTime = LocalTime.parse(allParams.get("reservedEndTime")).format(timeFormat);
		
		String formattedReservedDate = LocalDate.parse(allParams.get("reservedDate")).format(dateFormat);
		String formattedReservedEndDate = LocalDate.parse(allParams.get("reservedEndDate")).format(dateFormat);
		
		model.addAttribute("formatedReservedTime", formattedReservedTime);
		model.addAttribute("formatedReservedEndTime", formattedReservedEndTime);
		
		model.addAttribute("formattedReservedDate", formattedReservedDate);
		model.addAttribute("formattedReservedEndDate", formattedReservedEndDate);
		
		return "room_reservations_confirm";
	}
	
	@PostMapping("/processReservation")
	public String processRoomReservation(@RequestParam Map<String,String> allParams, 
			Model model,
			@RequestParam(required = false) String id,
			RedirectAttributes redirAttr) {
		
		logger.info("Start process>>>>>");
		
		int hospitalRoomId = Integer.parseInt(allParams.get("hospitalRoom"));
		String roomCode = allParams.get("roomCode");
		String reservedDate = allParams.get("reservedDate");
		String reservedTime = allParams.get("reservedTime");
		String reservedEndDate = allParams.get("reservedEndDate");
		String reservedEndTime = allParams.get("reservedEndTime");
		
		HospitalRoom hospitalRoom = hospitalRoomService.getHospitalRoom(hospitalRoomId);
		User user = authenticationFacade.getUser();
		
		//processing of room reservation for both edit and add only sends to one path.
		RoomReservation roomReservation = new RoomReservation();
		if (id != null) {
			logger.info("This process is editing...");
			roomReservation = roomReservationService.getRoomReservation(Integer.parseInt(id));
		}
		
		if (user != null) {
			UserDetail userDetail = user.getUserDetail();
			roomReservation.setReservedByUserId(String.valueOf(user.getId()));
			roomReservation.setCreatedBy(Util.makeFullName(userDetail));
			roomReservation.setUpdatedBy(Util.makeFullName(userDetail));
			roomReservation.setCreated(LocalDateTime.now());
			
			if (id != null) {
				roomReservation.setUpdatedBy(Util.makeFullName(userDetail));
				
			}
		}
		
		roomReservation.setHospitalRoom(hospitalRoom);
		roomReservation.setRoomCode(roomCode);
		roomReservation.setReservedDate(LocalDate.parse(reservedDate));
		roomReservation.setReservedTime(LocalTime.parse(reservedTime));
		roomReservation.setReservedEndDate(LocalDate.parse(reservedEndDate));
		roomReservation.setReservedEndTime(LocalTime.parse(reservedEndTime));
		roomReservation.setReservationStatus(0);
		
		roomReservation.setModified(LocalDateTime.now());
		roomReservationService.saveRoomReservation(roomReservation);
		redirAttr.addFlashAttribute("message", "<b>Save Room Reservation Successful!</b>");
		
		logger.info("End process>>>>>");
		return "redirect:/reservations";
	}
	
	@GetMapping("/edit/{id}")
	public String editReservation() {
		return "room_reservations_add";
	}
	
	@GetMapping("/details/{id}")
	public String viewReservation(@PathVariable int id, Model model) {
		RoomReservation roomReservation = roomReservationService.getRoomReservation(id);
		Map<String, String> attributes = new HashMap<>();
		
		attributes.put("roomCode", roomReservation.getRoomCode());
		attributes.put("hasAppointment", roomReservation.isHasAssociatedAppointment() ? "Yes" : "No");
		attributes.put("appointmentId", roomReservation.getAssociatedAppointmentId() != null ? String.valueOf(roomReservation.getAssociatedAppointmentId()) : "None");
		attributes.put("reservedDate", Util.formatDate(roomReservation.getReservedDate(), "dd/MM/yyyy"));
		attributes.put("reservedTime", Util.formatDate(roomReservation.getReservedTime(), "hh:mm a"));
		attributes.put("reservedEndDate", Util.formatDate(roomReservation.getReservedEndDate(), "dd/MM/yyyy"));
		attributes.put("reservedEndTime", Util.formatDate(roomReservation.getReservedEndTime(), "hh:mm a"));
		attributes.put("roomDetails", roomReservation.getHospitalRoom().getDescription());
		
		model.addAllAttributes(attributes);
		return "room_reservations_view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteReservation(@PathVariable int id, RedirectAttributes redirAttr) {
		RoomReservation roomReservation = roomReservationService.getRoomReservation(id);
		User user = authenticationFacade.getUser();
		
		if (user != null) {
			UserDetail userDetail = user.getUserDetail();
			roomReservation.setUpdatedBy(Util.makeFullName(userDetail));
			roomReservation.setModified(LocalDateTime.now());
		}
		
		roomReservation.setDeleted(true);
		roomReservation.setDeletedDate(LocalDateTime.now());
		roomReservationService.saveRoomReservation(roomReservation);
		redirAttr.addFlashAttribute("message", "<b>Reservation Delete Successful!</b>");
		
		return "redirect:/reservations";
	}
	
	@GetMapping("/update/{id}")
	public String updateReservation(@PathVariable int id, 
			@RequestParam String status,
			RedirectAttributes redirAttr) {
		
		RoomReservation roomReservation = roomReservationService.getRoomReservation(id);
		User user = authenticationFacade.getUser();
		
		if (user != null) {
			UserDetail userDetail = user.getUserDetail();
			roomReservation.setUpdatedBy(Util.makeFullName(userDetail));
			roomReservation.setModified(LocalDateTime.now());
			roomReservation.setReservationStatus(Integer.parseInt(status));
			roomReservationService.saveRoomReservation(roomReservation);
			redirAttr.addFlashAttribute("message", "<b>Reservation Update Successful!</b>");
		}
		
		return "redirect:/reservations";
	}
	
}

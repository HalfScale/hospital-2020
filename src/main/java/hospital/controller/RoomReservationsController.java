package hospital.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hospital.entity.RoomReservation;

@Controller
@RequestMapping("/reservations")
public class RoomReservationsController {
	
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
		System.out.println("All params: " + allParams);
		model.addAttribute("roomCode", allParams.get("roomCode"));
		model.addAttribute("reservedDate", allParams.get("reservedDate"));
		model.addAttribute("reservedTime", allParams.get("reservedTime"));
		model.addAttribute("reservedEndDate", allParams.get("reservedEndDate"));
		model.addAttribute("reservedEndTime", allParams.get("reservedEndTime"));
		model.addAttribute("hospitalRoom", allParams.get("hospitalRoom"));
		return "room_reservations_confirm";
	}
	
	@GetMapping("/edit/{id}")
	public String editReservation() {
		return "room_reservations_save";
	}
	
	@GetMapping("/details/{id}")
	public String viewReservation() {
		return "room_reservations_view";
	}
}

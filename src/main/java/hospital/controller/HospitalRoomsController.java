package hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospital_rooms")
public class HospitalRoomsController {
	
	@GetMapping
	public String hospitalRoomList() {
		return "hospital_rooms";
	}
	
	@GetMapping("/add")
	public String addHospitalRoom() {
		return "hospital_rooms_add_edit";
	}
	
	@GetMapping("/edit/{id}")
	public String editHospitalRoom() {
		return "hospital_rooms_add_edit";
	}
	
	@GetMapping("/details/{id}")
	public String viewHospitalRoom() {
		return "hospital_rooms_view";
	}
}

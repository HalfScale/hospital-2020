package hospital.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hospital.entity.HospitalRoom;
import hospital.service.HospitalRoomService;

@Controller
@RequestMapping("/hospital_rooms")
public class HospitalRoomsController {
	
	@Autowired
	private HospitalRoomService hospitalRoomService;
	
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
	
	@PostMapping
	public String addHospitalRoom(HospitalRoom hospitalRoom, 
			RedirectAttributes redirAttr, 
			@RequestParam(required = false) String id) {
		
		System.out.println("request param id: " + id);
		
		if(id != null) {
			hospitalRoom.setId(Integer.parseInt(id));
		}else {
			hospitalRoom.setCreated(LocalDateTime.now());
		}
		
		hospitalRoom.setCreatedBy("Marwin Buenaventura"); // Fix this if the login branch is integrated.
		hospitalRoom.setModified(LocalDateTime.now());
		hospitalRoomService.saveHospitalRoom(hospitalRoom);
		
		redirAttr.addFlashAttribute("message", "<b>Saving Hospital Room Successful!</b>");
		redirAttr.addFlashAttribute("status", "success");
		
		return "redirect:/hospital_rooms";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteHospitalRoom(@PathVariable int id, RedirectAttributes redirAttr) {
		hospitalRoomService.deleteHospitalRoom(id);
		redirAttr.addFlashAttribute("message", "<b>Hospital Room Deletion Successful!</b>");
		redirAttr.addFlashAttribute("status", "success");
		return "redirect:/hospital_rooms";
	}
	
}

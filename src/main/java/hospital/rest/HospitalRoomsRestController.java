package hospital.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.entity.HospitalRoom;
import hospital.entity.MyResponseObject;
import hospital.service.HospitalRoomService;

@RestController
@RequestMapping("/api")
public class HospitalRoomsRestController {
	
	@Autowired
	private HospitalRoomService hospitalRoomService;

	@GetMapping("/hospital_rooms")
	public List<HospitalRoom> getHospitalRooms() {
		return hospitalRoomService.getHospitalRooms();
	}
	
	@GetMapping("/hospital_rooms/{id}")
	public HospitalRoom getHospitalRoom(@PathVariable int id) {
		return hospitalRoomService.getHospitalRoom(id);
	}
	
	@PostMapping("/hospital_rooms")
	public MyResponseObject addHospitalRoom(@RequestBody HospitalRoom hospitalRoom) {
		hospitalRoomService.saveHospitalRoom(hospitalRoom);
		MyResponseObject response = new MyResponseObject();
		response.setData(hospitalRoom);
		response.setCode("202");
		response.setResponse("Saving successful");
		
		return response;
	}
	
	@PutMapping("/hospital_rooms")
	public MyResponseObject editHospitalRoom(@RequestBody HospitalRoom hospitalRoom) {
		hospitalRoomService.saveHospitalRoom(hospitalRoom);
		MyResponseObject response = new MyResponseObject();
		response.setData(hospitalRoom);
		response.setCode("202");
		response.setResponse("Succesfully updated Hospital Room Id = " + hospitalRoom.getId());
		
		return response;
	}
	
	@DeleteMapping("/hospital_rooms/{id}")
	public MyResponseObject deleteHospitalRoom(@PathVariable int id) {
		hospitalRoomService.deleteHospitalRoom(id);
		MyResponseObject response = new MyResponseObject();
		response.setCode("202");
		response.setResponse("Successfully deleted Hospital Room!");
		
		return response;
	}
	
}

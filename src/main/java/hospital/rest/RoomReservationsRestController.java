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

import hospital.entity.MyResponseObject;
import hospital.entity.RoomReservation;
import hospital.service.RoomReservationService;

@RestController
@RequestMapping("/api")
public class RoomReservationsRestController {
	
	@Autowired
	private RoomReservationService roomReservationService;
	
	@GetMapping("/room_reservations")
	public List<RoomReservation> getRoomReservations() {
		return roomReservationService.getRoomReservations();
	}
	
	@GetMapping("/room_reservations/{id}")
	public RoomReservation getRoomReservation(@PathVariable int id) {
		return roomReservationService.getRoomReservation(id);
	}
	
	@PostMapping("/room_reservations")
	public MyResponseObject saveRoomReservation(@RequestBody RoomReservation roomReservation) {
		roomReservationService.saveRoomReservation(roomReservation);
		MyResponseObject response = new MyResponseObject();
		response.setCode("202");
		response.setResponse("Saving successful!");
		response.setData(roomReservation);
		return response;
	}
	
	@PutMapping("/room_reservations")
	public MyResponseObject editRoomReservation(@RequestBody RoomReservation roomReservation) {
		roomReservationService.saveRoomReservation(roomReservation);
		MyResponseObject response = new MyResponseObject();
		response.setCode("202");
		response.setResponse("Update successful!");
		response.setData(roomReservation);
		return response;
	}
	
	@DeleteMapping("/room_reservations/{id}")
	public MyResponseObject deleteRoomReservation(@PathVariable int id) {
		roomReservationService.deleteRoomReservation(id);
		MyResponseObject response = new MyResponseObject();
		response.setCode("202");
		response.setResponse("Deletion successful!");
		return response;
	}
	
}

package hospital.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import hospital.entity.User;
import hospital.facade.IAuthenticationFacade;
import hospital.service.RoomReservationService;

@RestController
@RequestMapping("/api")
public class RoomReservationsRestController {
	private static final Logger logger = LogManager.getLogger(RoomReservationsRestController.class);
	
	@Autowired
	private RoomReservationService roomReservationService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@GetMapping("/room_reservations")
	public List<RoomReservation> getRoomReservations() {
		logger.debug("Accessing /room_reservations ->");
//		User user = authenticationFacade.getUser();
//		if (user != null) {
//			if (user.getUserType() == 2) {
//				return roomReservationService.getRoomReservations(user.getId());
//			}
//		}
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
		RoomReservation roomReservation = roomReservationService.getRoomReservation(id);
		roomReservation.setDeleted(true);
//		roomReservation.setDeletedDate(LocalDateTime.now());
		roomReservationService.saveRoomReservation(roomReservation);
		
		MyResponseObject response = new MyResponseObject();
		response.setCode("202");
		response.setResponse("Deletion successful!");
		return response;
	}
	
}

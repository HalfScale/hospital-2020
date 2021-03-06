package hospital.service;

import java.util.List;

import hospital.entity.RoomReservation;


public interface RoomReservationService {
	public List<RoomReservation> getRoomReservations();
	public void saveRoomReservation(RoomReservation roomReservation);
	public RoomReservation getRoomReservation(int id);
	public void deleteRoomReservation(int id);
}

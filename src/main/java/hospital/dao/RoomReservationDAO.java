package hospital.dao;

import java.util.List;

import hospital.entity.RoomReservation;

public interface RoomReservationDAO {
	public List<RoomReservation> getRoomReservations();
	public List<RoomReservation> getRoomReservations(int userId);
	public void saveRoomReservation(RoomReservation roomReservation);
	public RoomReservation getRoomReservation(int id);
	public void deleteRoomReservation(int id);
}

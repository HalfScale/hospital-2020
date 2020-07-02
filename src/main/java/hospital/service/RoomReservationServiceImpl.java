package hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.RoomReservationDAO;
import hospital.entity.RoomReservation;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

	@Autowired
	private RoomReservationDAO roomReservationDao;
	
	@Override
	@Transactional
	public List<RoomReservation> getRoomReservations() {
		return roomReservationDao.getRoomReservations();
	}

	@Override
	@Transactional
	public void saveRoomReservation(RoomReservation roomReservation) {
		roomReservationDao.saveRoomReservation(roomReservation);
	}

	@Override
	@Transactional
	public RoomReservation getRoomReservation(int id) {
		return roomReservationDao.getRoomReservation(id);
	}

	@Override
	@Transactional
	public void deleteRoomReservation(int id) {
		roomReservationDao.deleteRoomReservation(id);
	}
	
}

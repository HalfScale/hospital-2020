package hospital.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.HospitalRoomDAO;
import hospital.entity.HospitalRoom;

@Service
public class HospitalRoomServiceImpl implements HospitalRoomService {

	@Autowired
	private HospitalRoomDAO hospitalRoomDAO;
	
	@Override
	@Transactional
	public List<HospitalRoom> getHospitalRooms() {
		return hospitalRoomDAO.getHospitalRooms();
	}

	@Override
	@Transactional
	public void saveHospitalRoom(HospitalRoom hospitalRoom) {
		hospitalRoomDAO.saveHospitalRoom(hospitalRoom);
	}

	@Override
	@Transactional
	public HospitalRoom getHospitalRoom(int id) {
		return hospitalRoomDAO.getHospitalRoom(id);
	}

	@Override
	@Transactional
	public void deleteHospitalRoom(int id) {
		HospitalRoom hospitalRoom = hospitalRoomDAO.getHospitalRoom(id);
		hospitalRoomDAO.saveHospitalRoom(hospitalRoom);
	}

}

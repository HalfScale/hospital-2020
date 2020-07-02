package hospital.service;

import java.util.List;

import hospital.entity.HospitalRoom;

public interface HospitalRoomService {
	public List<HospitalRoom> getHospitalRooms();
	public void saveHospitalRoom(HospitalRoom hospitalRoom);
	public HospitalRoom getHospitalRoom(int id);
	public void deleteHospitalRoom(int id);
}

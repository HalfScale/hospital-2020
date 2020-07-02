package hospital.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.HospitalRoom;

@Repository
public class HospitalRoomDAOImpl implements HospitalRoomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<HospitalRoom> getHospitalRooms() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<HospitalRoom> theQuery = currentSession.createQuery("from HospitalRoom", HospitalRoom.class);
		
		return theQuery.getResultList();
	}

	@Override
	public void saveHospitalRoom(HospitalRoom hospitalRoom) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("User " + hospitalRoom.getId());
		currentSession.saveOrUpdate(hospitalRoom);
	}

	@Override
	public HospitalRoom getHospitalRoom(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		HospitalRoom hospitalRoom = currentSession.get(HospitalRoom.class, id);
		
		return hospitalRoom;
	}

	@Override
	public void deleteHospitalRoom(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from HospitalRoom where id=:id");
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
	}

}

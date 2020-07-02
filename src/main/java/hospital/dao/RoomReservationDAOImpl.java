package hospital.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.RoomReservation;

@Repository
public class RoomReservationDAOImpl implements RoomReservationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RoomReservation> getRoomReservations() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<RoomReservation> theQuery = currentSession.createQuery("from RoomReservation", RoomReservation.class);
		
		return theQuery.getResultList();
	}

	@Override
	public void saveRoomReservation(RoomReservation roomReservation) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(roomReservation);
	}

	@Override
	public RoomReservation getRoomReservation(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(RoomReservation.class, id);
	}

	@Override
	public void deleteRoomReservation(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from RoomReservation where id=:id");
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();

	}

}

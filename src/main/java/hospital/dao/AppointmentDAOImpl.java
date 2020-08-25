package hospital.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.Appointment;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Appointment> getAppointments() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Appointment> query = currentSession.createQuery("from Appointment where deleted = 0", Appointment.class);
		return query.getResultList();
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		// TODO Auto-generated method stub

	}

	@Override
	public Appointment getAppointment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAppointment(int id) {
		// TODO Auto-generated method stub

	}

}

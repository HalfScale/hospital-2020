package hospital.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.AppointmentDetail;

@Repository
public class AppointmentDetailDAOImpl implements AppointmentDetailDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AppointmentDetail> getAppointmentDetails() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<AppointmentDetail> query = currentSession.createQuery("from AppointmentDetail where deleted = 0");
		return query.getResultList();
	}
	
	@Override
	public List<AppointmentDetail> getAppointmentDetailsByPatient(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "select ad from AppointmentDetail ad inner join ad.appointment a where a.patientId =:id and a.deleted = 0";
		
		Query<AppointmentDetail> query = currentSession.createQuery(hql, AppointmentDetail.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	@Override
	public List<AppointmentDetail> getAppointmentDetailsByDoctor(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "select ad from AppointmentDetail ad inner join ad.appointment a where a.doctorId =:id and a.deleted = 0";
		
		Query<AppointmentDetail> query = currentSession.createQuery(hql, AppointmentDetail.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}

	@Override
	public void saveAppointmentDetail(AppointmentDetail appointmentDetail) {
		// TODO Auto-generated method stub

	}

	@Override
	public AppointmentDetail getAppointmentDetail(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAppointmentDetail(int id) {
		// TODO Auto-generated method stub

	}
	
}

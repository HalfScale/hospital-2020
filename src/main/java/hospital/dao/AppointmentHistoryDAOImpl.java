package hospital.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.AppointmentHistory;

@Repository
public class AppointmentHistoryDAOImpl implements AppointmentHistoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AppointmentHistory> getAppointmentHistories() {
		return null;
	}

	@Override
	public void saveAppointmentHistory(AppointmentHistory appointmentHistory) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(appointmentHistory);
	}

	@Override
	public AppointmentHistory getAppointmentHistory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAppointmentHistory(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AppointmentHistory> getHistoriesByAppointmentId(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "select ah from AppointmentHistory ah inner join ah.appointment a where a.id=:id and a.deleted = 0";
		
		Query<AppointmentHistory> theQuery = currentSession.createQuery(hql, AppointmentHistory.class);
		theQuery.setParameter("id", id);
		
		return theQuery.getResultList();
	}

}

package hospital.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.DoctorCode;
import hospital.entity.HospitalRoom;
import hospital.entity.User;

@Repository
public class DoctorCodeDAOImpl implements DoctorCodeDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DoctorCode> getDoctorCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDoctorCode(DoctorCode doctorCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DoctorCode getDoctorCode(Integer code) {
		Session session = sessionFactory.getCurrentSession(); 
		
		Query<DoctorCode> query = session.createQuery("from DoctorCode where doctorCode=:doctorCode and deleted = 0", DoctorCode.class);
		query.setParameter("doctorCode", code);
		
		DoctorCode doctorCode = null;
		
		try {
			doctorCode = query.getSingleResult();
		} catch (NoResultException e) {}

		
		return doctorCode;
	}

	@Override
	public void deleteDoctorCode(int id) {
		// TODO Auto-generated method stub
		
	}

}

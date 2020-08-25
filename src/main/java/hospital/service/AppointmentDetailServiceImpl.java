package hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.AppointmentDetailDAO;
import hospital.entity.AppointmentDetail;

@Service
public class AppointmentDetailServiceImpl implements AppointmentDetailService {
	
	@Autowired
	private AppointmentDetailDAO appointmentDetailDao;

	@Override
	@Transactional
	public List<AppointmentDetail> getAppointmentDetails() {
		return appointmentDetailDao.getAppointmentDetails();
	}
	
	@Override
	@Transactional
	public List<AppointmentDetail> getAppointmentDetailsByPatient(int id) {
		return appointmentDetailDao.getAppointmentDetailsByPatient(id);
	}
	
	@Override
	@Transactional
	public List<AppointmentDetail> getAppointmentDetailsByDoctor(int id) {
		return appointmentDetailDao.getAppointmentDetailsByDoctor(id);
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

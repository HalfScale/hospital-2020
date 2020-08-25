package hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.AppointmentDAO;
import hospital.entity.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentDAO appointmentDao;

	@Override
	@Transactional
	public List<Appointment> getAppointments() {
		return appointmentDao.getAppointments();
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

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
	@Transactional
	public void saveAppointment(Appointment appointment) {
		appointmentDao.saveAppointment(appointment);
	}

	@Override
	@Transactional
	public Appointment getAppointment(int id) {
		return appointmentDao.getAppointment(id);
	}

	@Override
	public void deleteAppointment(int id) {
		// TODO Auto-generated method stub

	}

}

package hospital.service;

import java.util.List;

import hospital.entity.Appointment;

public interface AppointmentService {
	public List<Appointment> getAppointments();
	public void saveAppointment(Appointment appointment);
	public Appointment getAppointment(int id);
	public void deleteAppointment(int id);
}

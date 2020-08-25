package hospital.dao;

import java.util.List;

import hospital.entity.Appointment;

public interface AppointmentDAO {
	public List<Appointment> getAppointments();
	public void saveAppointment(Appointment appointment);
	public Appointment getAppointment(int id);
	public void deleteAppointment(int id);
}

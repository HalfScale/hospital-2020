package hospital.dao;

import java.util.List;

import hospital.entity.AppointmentHistory;

public interface AppointmentHistoryDAO {
	public List<AppointmentHistory> getAppointmentHistories();
	public List<AppointmentHistory> getHistoriesByAppointmentId(int id);
	public void saveAppointmentHistory(AppointmentHistory appointmentHistory);
	public AppointmentHistory getAppointmentHistory(int id);
	public void deleteAppointmentHistory(int id);
}

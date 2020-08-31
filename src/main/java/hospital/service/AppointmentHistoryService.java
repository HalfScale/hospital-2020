package hospital.service;

import java.util.List;

import hospital.entity.AppointmentHistory;

public interface AppointmentHistoryService {
	List<AppointmentHistory> getAppointmentHistories();
	List<AppointmentHistory> getHistoriesByAppointmentId(int id);
	void saveAppointmentHistory(AppointmentHistory appointmentHistory);
	AppointmentHistory getAppointmentHistory(int id);
	void deleteAppointmentHistory(int id);
}

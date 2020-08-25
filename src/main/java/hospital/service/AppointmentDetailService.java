package hospital.service;

import java.util.List;

import hospital.entity.AppointmentDetail;

public interface AppointmentDetailService {
	public List<AppointmentDetail> getAppointmentDetails();
	public List<AppointmentDetail> getAppointmentDetailsByPatient(int id);
	public List<AppointmentDetail> getAppointmentDetailsByDoctor(int id);
	public void saveAppointmentDetail(AppointmentDetail appointmentDetail);
	public AppointmentDetail getAppointmentDetail(int id);
	public void deleteAppointmentDetail(int id);
}

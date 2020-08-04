package hospital.service;

import java.util.List;

import hospital.entity.DoctorCode;

public interface DoctorCodeService {
	public List<DoctorCode> getDoctorCodes();
	public void saveDoctorCode(DoctorCode doctorCode);
	public DoctorCode getDoctorCode(int id);
	public void deleteDoctorCode(int id);
	public DoctorCode getDoctorCodeByCode(int code);
}

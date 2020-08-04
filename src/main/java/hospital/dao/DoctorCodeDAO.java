package hospital.dao;

import java.util.List;

import hospital.entity.DoctorCode;

public interface DoctorCodeDAO {
	public List<DoctorCode> getDoctorCodes();
	public void saveDoctorCode(DoctorCode doctorCode);
	public DoctorCode getDoctorCode(Integer id);
	public void deleteDoctorCode(int id);
	public DoctorCode getDoctorCodeByCode(int code);
}

package hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.DoctorCodeDAO;
import hospital.entity.DoctorCode;

@Service
public class DoctorCodeServiceImpl implements DoctorCodeService {
	
	@Autowired
	private DoctorCodeDAO doctorCodeDao;

	@Override
	public List<DoctorCode> getDoctorCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDoctorCode(DoctorCode doctorCode) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public DoctorCode getDoctorCode(int code) {
		DoctorCode doctorCode =  doctorCodeDao.getDoctorCode(code);
		return doctorCode != null ? doctorCode : new DoctorCode();
	}

	@Override
	public void deleteDoctorCode(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public DoctorCode getDoctorCodeByCode(int code) {
		return null;
	}

}

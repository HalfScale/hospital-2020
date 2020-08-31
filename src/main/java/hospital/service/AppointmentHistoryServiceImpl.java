package hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.AppointmentHistoryDAO;
import hospital.entity.AppointmentHistory;

@Service
public class AppointmentHistoryServiceImpl implements AppointmentHistoryService {
	
	@Autowired
	private AppointmentHistoryDAO appointmentHistoryDao;

	@Override
	public List<AppointmentHistory> getAppointmentHistories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<AppointmentHistory> getHistoriesByAppointmentId(int id) {
		return appointmentHistoryDao.getHistoriesByAppointmentId(id);
	}

	@Override
	@Transactional
	public void saveAppointmentHistory(AppointmentHistory appointmentHistory) {
		appointmentHistoryDao.saveAppointmentHistory(appointmentHistory);
	}

	@Override
	public AppointmentHistory getAppointmentHistory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAppointmentHistory(int id) {
		// TODO Auto-generated method stub

	}

}

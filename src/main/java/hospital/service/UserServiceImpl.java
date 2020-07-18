package hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.UserDAO;
import hospital.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;

	@Override
	@Transactional
	public List<User> getUsers() {
//		return userDao.getUsers();
		return null;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.saveUser(user);

	}

	@Override
	@Transactional
	public User getUser(int id) {
//		return userDao.getUser(id);
		return null;
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
//		userDao.deleteUser(id);
	}

	@Override
	@Transactional
	public User getUser(String email) {
		return userDao.findUserByUsername(email);
	}
	
	

}

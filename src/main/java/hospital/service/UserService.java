package hospital.service;

import java.util.List;

import hospital.entity.User;

public interface UserService {
	public List<User> getUsers();
	public void saveUser(User user);
	public User getUser(String identifier); 
	public User getUser(int id);
	public void deleteUser(int id);
	public User findEmail(String email);
	public User findByToken(String token);
	public List<User> findByType(int type);
}

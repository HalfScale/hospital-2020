package hospital.dao;

import hospital.entity.User;

public interface UserDetailsDao {
	public User findUserByUsername(String username);
}

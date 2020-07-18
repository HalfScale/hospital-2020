package hospital.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.User;



@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User findUserByUsername(String email) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("from User where email=:email", User.class);
		query.setParameter("email", email);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException  e) { /* leave it blank*/ }
		
		return user != null ? user : null;
	}

}

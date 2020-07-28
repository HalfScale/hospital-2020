package hospital.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hospital.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> theQuery = currentSession.createQuery("from User", User.class);

		List<User> users = theQuery.getResultList();

		return users;
	}

	@Override
	public void saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("User " + user.getId());
		currentSession.saveOrUpdate(user);
	}

	@Override
	public User getUser(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(User.class, id);
	}

	@Override
	public void deleteUser(int id) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		
//		Query query = currentSession.createQuery("delete from User where id=:id");
//		query.setParameter("id", id);
//		query.executeUpdate();

	}

	@Override
	public User findUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "select u from User u left join u.userDetail detail where u.email=:email and "
				+ "detail is not null and u.isConfirmed = 1 and u.enabled = 1 and u.deleted = 0 and "
				+ "u.userType in(1, 2, 3)";

		Query<User> query = session.createQuery(hql, User.class);
		query.setParameter("email", email);

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {}

		return user != null ? user : null;
	}

	@Override
	public User findEmail(String email) {
		System.out.println("find email: " + email + "");
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("from User where email like :email and deleted = 0", User.class);
		query.setParameter("email", "%" + email + "%");

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {}

		return user != null ? user : null;
	}

	@Override
	public User findByToken(String token) {
		System.out.println("find token: " + token + "");
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("from User where registrationToken like :token", User.class);
		query.setParameter("token", "%" + token + "%");

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {e.printStackTrace();}

		return user != null ? user : null;
	}

	
}

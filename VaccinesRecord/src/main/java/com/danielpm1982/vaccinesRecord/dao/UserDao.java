package com.danielpm1982.vaccinesRecord.dao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.vaccinesRecord.entity.User;

@Repository
public class UserDao implements UserDaoInterface {
	private EntityManager entityManager;
	@Autowired
	public UserDao(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	public User findByUserName(String userName) {
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<User> query = session.createQuery("from User where userName=:uName", User.class);
		query.setParameter("uName", userName);
		User user = null;
		try {
			user = query.getSingleResult();
			user.toString(); //for lazily loading the role table data before Session closes, avoiding errors.
		} catch (Exception e) {
			user = null;
		}
		return user;
	}
	@Override
	public User findByUserId(Long userId) {
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<User> query = session.createQuery("from User where id=:uId", User.class);
		query.setParameter("uId", userId);
		User user = null;
		try {
			user = query.getSingleResult();
			user.toString(); //for lazily loading the role table data before Session closes, avoiding errors.
		} catch (Exception e) {
			user = null;
		}
		return user;
	}
	@Override
	public void save(User user) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
	}
	@Override
	public void deleteUserById(Long userId) {
		User userToBeDeleted = findByUserId(userId);
		Session session = entityManager.unwrap(Session.class);
		session.delete(userToBeDeleted);
	}
}

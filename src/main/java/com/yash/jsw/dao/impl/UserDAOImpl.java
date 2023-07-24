package com.yash.jsw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.jsw.dao.UserDAO;
import com.yash.jsw.model.User;
import com.yash.jsw.utility.GlobalConstant;

@Repository
public class UserDAOImpl implements UserDAO {
	static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addUser(User user) throws Exception {
		logger.debug("UserDAOImpl.addUser() - [" + user.getUsername() + "]");
		getCurrentSession().save(user);
	}

	@Override
	public User getUser(int userId) throws Exception {
		logger.debug("UserDAOImpl.getUser() - [" + userId + "]");
		return (User) getCurrentSession().get(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String usersName) throws Exception {
		logger.debug("UserDAOImpl.getUser() - [" + usersName + "]");
		Query query = getCurrentSession().createQuery("from User where username = :usersName ");
		query.setString("usersName", usersName);

		logger.debug(query.toString());
		List<User> list = (List<User>)query.list();
		if (list!=null && !list.isEmpty() ) {
			User userObject = (User) list.get(0);
			return userObject;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean checkUser(String usersName,Integer id) throws Exception {
		logger.debug("UserDAOImpl.getUser() - [" + usersName + "]");
		Query query = getCurrentSession().createQuery("from User where username = :usersName and id!=:id");
		query.setString("usersName", usersName);
		query.setInteger("id", id);

		logger.debug(query.toString());
		List<User> list = (List<User>)query.list();
		if (list!=null && !list.isEmpty() ) {
			return true;
		} 
		return false;
	}

	@Override
	public Integer updateUser(User user) throws Exception {
		if(!checkUser(user.getUsername(),user.getId())){
			User userToUpdate = getUser(user.getId());
			userToUpdate.setEnabled(user.getEnabled());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setRole(user.getRole());
			userToUpdate.setEnabled(user.getEnabled());
			getCurrentSession().update(userToUpdate);
		}else{
			return GlobalConstant.OBJECT_DUPLICATE;
		}
		return GlobalConstant.ACTION_SUCCESSFUL;
	}

	@Override
	public void deleteUser(int userId) throws Exception {
		User user = getUser(userId);
		if (user != null) {
			getCurrentSession().delete(user);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		String hql = "FROM User u ORDER BY u.id";
		return getCurrentSession().createQuery(hql).list();
	}
}

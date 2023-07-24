package com.yash.jsw.service.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.jsw.dao.UserDAO;
import com.yash.jsw.exception.UserNotFoundException;
import com.yash.jsw.model.User;
import com.yash.jsw.service.user.UserService;
import com.yash.jsw.utility.GlobalConstant;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Autowired
    private UserDAO userDAO;

	@Override
	public Integer addUser(User user) throws Exception {
		User userCheck = this.getUser(user.getUsername());
		if(userCheck!=null){
			return GlobalConstant.OBJECT_DUPLICATE;
		}else{
			userDAO.addUser(user);
			return GlobalConstant.ACTION_SUCCESSFUL;
		}
	}

    @Override
    public User getUser(int userId) throws Exception {
        return userDAO.getUser(userId);
    }

	@Override
	public User getUser(String username) throws Exception {
		return userDAO.getUser(username);
	}

	@Override
	public Integer updateUser(User user) throws Exception {
		return userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(int userId) throws Exception {
		userDAO.deleteUser(userId);
	}

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return getUser(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}

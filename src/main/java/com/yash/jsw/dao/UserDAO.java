package com.yash.jsw.dao;

import java.util.List;

import com.yash.jsw.model.User;

public interface UserDAO {

    public void addUser(User user) throws Exception;

    public User getUser(int userId) throws Exception;
    
    public User getUser(String username) throws Exception;

    public Integer updateUser(User user) throws Exception;

    public void deleteUser(int userId) throws Exception;

    public List<User> getUsers();

	Boolean checkUser(String usersName, Integer id) throws Exception;

}

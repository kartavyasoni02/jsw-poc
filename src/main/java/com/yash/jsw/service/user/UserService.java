package com.yash.jsw.service.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yash.jsw.model.User;

public interface UserService extends UserDetailsService {

    public Integer addUser(User user) throws Exception;

    public User getUser(int userId) throws Exception;

    public User getUser(String username) throws Exception;

    public Integer updateUser(User user) throws Exception;

    public void deleteUser(int userId) throws Exception;

    public List<User> getUsers() throws Exception;
}

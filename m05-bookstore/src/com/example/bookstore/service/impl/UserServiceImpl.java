package com.example.bookstore.service.impl;

import com.example.bookstore.dao.UserDAO;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname, pwd);
    }

    @Override
    public void register(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUserByUname(String uname) {
        return userDAO.getUserByUname(uname);
    }
}

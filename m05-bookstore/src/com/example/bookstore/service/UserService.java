package com.example.bookstore.service;

import com.example.bookstore.pojo.User;

public interface UserService {
    User login(String uname, String pwd);
}

package com.example.bookstore.dao;

import com.example.bookstore.pojo.User;

public interface UserDAO {
    User getUser(String uname, String pwd);
}

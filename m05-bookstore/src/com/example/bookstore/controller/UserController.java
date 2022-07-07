package com.example.bookstore.controller;

import com.example.bookstore.pojo.User;
import com.example.bookstore.service.UserService;

public class UserController {

    private UserService userService;

    public String login(String uname, String pwd) {
        User user = userService.login(uname, pwd);
        System.out.println("user : " + user);
        return "index";
    }
}

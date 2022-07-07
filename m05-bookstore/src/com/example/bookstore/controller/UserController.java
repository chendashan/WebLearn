package com.example.bookstore.controller;

import com.example.bookstore.pojo.User;
import com.example.bookstore.service.UserService;

import javax.servlet.http.HttpSession;

public class UserController {

    private UserService userService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            session.setAttribute("currentUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }
}

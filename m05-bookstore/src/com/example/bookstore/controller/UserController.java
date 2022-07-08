package com.example.bookstore.controller;

import com.example.bookstore.pojo.Cart;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.CartItemService;
import com.example.bookstore.service.UserService;

import javax.servlet.http.HttpSession;

public class UserController {

    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);

            session.setAttribute("currentUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }

    public String logout(HttpSession session) {
        session.setAttribute("currentUser", null);
        return "user/login";
    }
}

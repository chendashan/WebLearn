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

    public String register(String uname, String pwd, String email, String verifyCode, HttpSession session) {
        Object objKapt = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (objKapt == null) {
            return "user/regist";
        } else {
            if (verifyCode.equals(objKapt)) {
                userService.register(new User(uname, pwd, email, 0));
            }
            return "user/login";
        }
    }
}

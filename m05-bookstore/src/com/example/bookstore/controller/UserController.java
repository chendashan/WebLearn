package com.example.bookstore.controller;

import com.example.bookstore.pojo.Cart;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.CartItemService;
import com.example.bookstore.service.UserService;
import com.example.myssm.util.Constants;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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

    public String register(String uname, String pwd, String email, String verifyCode, HttpSession session, HttpServletResponse response) throws IOException {
        Object objKapt = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (objKapt == null) {
            return "user/regist";
        } else {
            if (verifyCode.equals(objKapt)) {
                userService.register(new User(uname, pwd, email, 0));
                return "user/login";
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript'>alert('验证码不正确！');window.location.href='page.do?operate=page&page=user/regist'</script>");
                return Constants.RETURN_SCRIPT;
            }
        }
    }

    public String checkUname(String uname) {
        User user = userService.getUserByUname(uname);
        if (user != null) {
            return "json:{\"uname\":1}";
        } else {
            return "json:{\"uname\":0}";
        }
    }
}

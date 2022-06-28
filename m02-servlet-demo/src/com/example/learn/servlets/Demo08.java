package com.example.learn.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//测试application保存作用域范围 Demo07 Demo08
@WebServlet("/demo08")
public class Demo08 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = req.getServletContext();
        Object object = application.getAttribute("username");

        System.out.println("application作用域： " + object);
    }
}

package com.example.learn.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo05 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Demo05   服务器转发或客户端重定向");

        //req.getRequestDispatcher("demo06").forward(req, resp);
        resp.sendRedirect("demo06");
    }
}

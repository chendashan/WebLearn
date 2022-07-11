package com.example.learn.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/axios02.do")
public class Axios02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("");
        BufferedReader bufferedReader = request.getReader();
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        str = sb.toString();

        System.out.println(str);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("{\"uname\":\"马云\",\"pwd\":\"88888888\"}");
    }
}

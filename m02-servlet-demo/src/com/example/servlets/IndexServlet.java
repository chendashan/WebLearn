package com.example.servlets;

import com.example.fruit.dao.FruitDAO;
import com.example.fruit.dao.impl.FruitDAOImpl;
import com.example.fruit.pojo.Fruit;
import com.example.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> list = fruitDAO.getFruitList();

        HttpSession session = req.getSession();
        session.setAttribute("fruit_list", list);

        super.processTemplate("fruit", req, resp);

    }
}

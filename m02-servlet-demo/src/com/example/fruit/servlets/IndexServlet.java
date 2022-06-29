package com.example.fruit.servlets;

import com.example.fruit.dao.FruitDAO;
import com.example.fruit.dao.impl.FruitDAOImpl;
import com.example.fruit.pojo.Fruit;
import com.example.myssm.myspringmvc.ViewBaseServlet;
import com.example.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        Integer page = 1;
        String pageStr = req.getParameter("page");
        if (StringUtil.isNotEmpty(pageStr)) {
            page = Integer.parseInt(pageStr);
        }

        HttpSession session = req.getSession();
        session.setAttribute("page", page);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> list = fruitDAO.getFruitPageList(page);

        session.setAttribute("fruit_list", list);

        int fruitCount = fruitDAO.getFruitCount();
        //总页数
        int pageCount = (fruitCount - 1) / 5 + 1;

        session.setAttribute("pageCount", pageCount);

        super.processTemplate("index", req, resp);

    }
}

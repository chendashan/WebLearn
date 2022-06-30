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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        Integer page = 1;

        String keyword = null;
        String oper = req.getParameter("oper");
        if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
            page = 1;
            keyword = req.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            String pageStr = req.getParameter("page");
            if (StringUtil.isNotEmpty(pageStr)) {
                page = Integer.parseInt(pageStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }


        session.setAttribute("page", page);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> list = fruitDAO.getFruitPageList(keyword, page);

        session.setAttribute("fruit_list", list);

        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount - 1) / 5 + 1;

        session.setAttribute("pageCount", pageCount);

        super.processTemplate("index", req, resp);

    }
}

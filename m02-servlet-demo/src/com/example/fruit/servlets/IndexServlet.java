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
        //设置当前页，默认值1
        Integer page = 1;

        String keyword = null;

        //如果oper!=null 说明通过表单的查询按钮过来的
        //如果 oper 是空的，说明不是通过表单的查询按钮点击过来的
        String oper = req.getParameter("oper");
        if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
            //说明是点击表单查询发送过来的请求
            page = 1;
            keyword = req.getParameter("keyword");
            //防止查询时拼接成%null%，期望的是%%
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            //要把keyword保存（覆盖）到session中
            session.setAttribute("keyword", keyword);
        } else {
            //非点击查询按钮的请求
            String pageStr = req.getParameter("page");
            if (StringUtil.isNotEmpty(pageStr)) {
                page = Integer.parseInt(pageStr);
            }
            //查询关键字根据session中的keyword
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        //重新更新当前页的值
        session.setAttribute("page", page);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> list = fruitDAO.getFruitPageList(keyword, page);

        session.setAttribute("fruit_list", list);
        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount - 1) / 5 + 1;

        session.setAttribute("pageCount", pageCount);

        super.processTemplate("index", req, resp);

    }
}

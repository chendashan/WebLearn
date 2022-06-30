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

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        switch (operate) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "del":
                delete(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "update":
                update(request, response);
                break;

            default:
                throw new RuntimeException("operate值非法！");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 设置编码
        request.setCharacterEncoding("utf-8");

        //2. 获取参数
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int count = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        //执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, count, remark));

        //资源跳转
        //super.processTemplate("index", request, response);
        //相当于request.getRequestDispatcher("index.html").forward(request, response);

        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取数据
        response.sendRedirect("fruit.do");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            super.processTemplate("edit", req, resp);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);

            fruitDAO.delFruit(fid);

            response.sendRedirect("fruit.do");
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int count = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0, name, price, count, remark);
        fruitDAO.addFruit(fruit);

        response.sendRedirect("fruit.do");
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

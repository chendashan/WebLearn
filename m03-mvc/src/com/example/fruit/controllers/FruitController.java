package com.example.fruit.controllers;

import com.example.fruit.pojo.Fruit;
import com.example.fruit.service.FruitService;
import com.example.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class FruitController {

    private FruitService fruitService = null;

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {

        //执行更新
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        //资源跳转
        //super.processTemplate("index", request, response);
        //相当于request.getRequestDispatcher("index.html").forward(request, response);

        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取数据
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest request) {
        if (fid != null) {
            Fruit fruit = fruitService.getFruitById(fid);
            request.setAttribute("fruit", fruit);
            //super.processTemplate("edit", req, resp);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid) {
        if (fid != null) {
            fruitService.delFruit(fid);

            //response.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitService.addFruit(fruit);

        return "redirect:fruit.do";
    }

    private String index(String oper, String keyword, Integer page, HttpServletRequest request) {

        HttpSession session = request.getSession();
        //设置当前页，默认值1
        if (page == null) {
            page = 1;
        }

        //如果oper!=null 说明通过表单的查询按钮过来的
        //如果 oper 是空的，说明不是通过表单的查询按钮点击过来的
        if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
            //说明是点击表单查询发送过来的请求
            page = 1;
            //防止查询时拼接成%null%，期望的是%%
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            //要把keyword保存（覆盖）到session中
            session.setAttribute("keyword", keyword);
        } else {
            //非点击查询按钮的请求

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

        List<Fruit> list = fruitService.getFruitList(keyword, page);

        session.setAttribute("fruit_list", list);
        //总页数
        int pageCount = fruitService.getPageCount(keyword);

        session.setAttribute("pageCount", pageCount);

        //super.processTemplate("index", req, resp);
        return "index";
    }
}

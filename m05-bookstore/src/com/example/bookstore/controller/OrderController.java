package com.example.bookstore.controller;

import com.example.bookstore.pojo.OrderBean;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.OrderService;
import com.example.myssm.util.StringUtil;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderController {

    private OrderService orderService;

    public String checkout(HttpSession session) {
        OrderBean orderBean = new OrderBean();
        Date date = new Date();

        String no = UUID.randomUUID() + "_" + StringUtil.trimDateSymbol(date);
        orderBean.setOrderNo(no);
        orderBean.setOrderDate(date);

        User user = (User) session.getAttribute("currentUser");
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);

        return "index";
    }
}

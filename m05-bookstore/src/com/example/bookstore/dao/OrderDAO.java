package com.example.bookstore.dao;

import com.example.bookstore.pojo.OrderBean;
import com.example.bookstore.pojo.User;

import java.util.List;

public interface OrderDAO {
    //添加订单
    void addOrderBean(OrderBean orderBean);

    //获取指定用户的订单
    List<OrderBean> getOrderList(User user);

    Integer getOrderTotalBookCount(OrderBean orderBean);
}

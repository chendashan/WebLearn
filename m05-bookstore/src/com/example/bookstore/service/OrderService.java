package com.example.bookstore.service;

import com.example.bookstore.pojo.OrderBean;
import com.example.bookstore.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);
}

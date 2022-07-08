package com.example.bookstore.dao;

import com.example.bookstore.pojo.OrderItem;

public interface OrderItemDAO {
    //添加订单项
    void addOrderItem(OrderItem orderItem);
}

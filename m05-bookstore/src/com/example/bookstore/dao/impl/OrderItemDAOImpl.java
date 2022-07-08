package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderItemDAO;
import com.example.bookstore.pojo.OrderItem;
import com.example.myssm.basedao.BaseDAO;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0, ?, ?, ?)", orderItem.getBook().getId(), orderItem.getBuyCount(), orderItem.getOrderBean().getId());
    }
}

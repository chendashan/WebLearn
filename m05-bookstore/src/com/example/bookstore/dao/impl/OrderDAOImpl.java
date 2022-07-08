package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDAO;
import com.example.bookstore.pojo.OrderBean;
import com.example.myssm.basedao.BaseDAO;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = super.executeUpdate("insert into t_order values(0, ?, ?, ?, ?, ?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
    }
}

package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDAO;
import com.example.bookstore.pojo.OrderBean;
import com.example.bookstore.pojo.User;
import com.example.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = super.executeUpdate("insert into t_order values(0, ?, ?, ?, ?, ?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return super.executeQuery("select * from t_order where orderUser = ?", user.getId());
    }

    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
        String sql = "select sum(t3.buyCount) as totalBookCount, t3.orderBean FROM (" +
                "SELECT t1.id, t2.buyCount, t2.orderBean from t_order t1 INNER JOIN t_order_item t2 " +
                "ON t1.id = t2.orderBean WHERE t1.orderUser = ? ) " +
                "t3 WHERE t3.orderBean = ? GROUP BY t3.orderBean;";
        Object[] objects = executeComplexQuery(sql, orderBean.getOrderUser().getId(), orderBean.getId());
        if (objects != null && objects.length > 0) {
            int count = ((BigDecimal) objects[0]).intValue();
            return count;
        }
        //return ((BigDecimal) executeComplexQuery(sql, orderBean.getOrderUser().getId(), orderBean.getId())[0]).intValue();
        return 0;
    }

}

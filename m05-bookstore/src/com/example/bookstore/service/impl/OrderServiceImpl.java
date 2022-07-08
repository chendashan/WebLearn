package com.example.bookstore.service.impl;

import com.example.bookstore.dao.OrderDAO;
import com.example.bookstore.dao.OrderItemDAO;
import com.example.bookstore.pojo.CartItem;
import com.example.bookstore.pojo.OrderBean;
import com.example.bookstore.pojo.OrderItem;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.CartItemService;
import com.example.bookstore.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemService cartItemService;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        //1. 添加一条记录
        orderDAO.addOrderBean(orderBean);

        //2. 订单详情表添加n条数据
        User user = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = user.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //3. 购物车项表中需要删除对应的n条记录
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemService.delCartItem(cartItem.getId());
        }
    }
}

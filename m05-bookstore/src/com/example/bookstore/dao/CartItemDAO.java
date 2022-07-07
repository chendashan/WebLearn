package com.example.bookstore.dao;

import com.example.bookstore.pojo.CartItem;

public interface CartItemDAO {

    //新增购物车项
    void addCartItem(CartItem cartItem);

    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);
}

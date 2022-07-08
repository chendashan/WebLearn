package com.example.bookstore.service;

import com.example.bookstore.pojo.Cart;
import com.example.bookstore.pojo.CartItem;
import com.example.bookstore.pojo.User;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //加载指定用户的购物车信息
    Cart getCart(User user);

    void delCartItem(Integer cartItemId);
}

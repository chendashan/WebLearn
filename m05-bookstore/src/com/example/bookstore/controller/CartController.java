package com.example.bookstore.controller;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Cart;
import com.example.bookstore.pojo.CartItem;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.CartItemService;

import javax.servlet.http.HttpSession;

public class CartController {

    private CartItemService cartItemService;

    //加载当前用户的购物车信息
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currentUser", user);
        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);

        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        return "redirect:cart.do";
    }
}

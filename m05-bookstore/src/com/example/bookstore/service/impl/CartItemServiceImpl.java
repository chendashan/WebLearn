package com.example.bookstore.service.impl;

import com.example.bookstore.dao.CartItemDAO;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Cart;
import com.example.bookstore.pojo.CartItem;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //将指定的图书添加到当前用户的购物车中，如果购物车已经存在则数量+1
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                updateCartItem(cartItemTemp);
            } else {
                addCartItem(cartItem);
            }
        } else {
            addCartItem(cartItem);
        }
    }

    private List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for (CartItem cartItem : cartItemList) {
            Book book = bookService.getBookById(cartItem.getBook().getId());
            cartItem.setBook(book);
        }
        return cartItemList;
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer, CartItem> map = new HashMap<>();

        if (cartItemList != null) {
            for (CartItem cartItem : cartItemList) {
                map.put(cartItem.getBook().getId(), cartItem);
            }
        }
        Cart cart = new Cart();
        cart.setCartItemMap(map);
        return cart;
    }

    @Override
    public void delCartItem(Integer cartItemId) {
        cartItemDAO.delCartItem(cartItemId);
    }
}

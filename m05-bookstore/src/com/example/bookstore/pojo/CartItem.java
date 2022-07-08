package com.example.bookstore.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;
    private Double totalMoney;

    public CartItem() {
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public Double getTotalMoney() {
        BigDecimal bdcPrice = new BigDecimal("" + book.getPrice());
        BigDecimal bdcCount = new BigDecimal("" + buyCount);
        totalMoney = bdcPrice.multiply(bdcCount).doubleValue();
        return totalMoney;
    }
}

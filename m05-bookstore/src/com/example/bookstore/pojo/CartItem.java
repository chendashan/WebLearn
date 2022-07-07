package com.example.bookstore.pojo;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCont;
    private User user;

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

    public Integer getBuyCont() {
        return buyCont;
    }

    public void setBuyCont(Integer buyCont) {
        this.buyCont = buyCont;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

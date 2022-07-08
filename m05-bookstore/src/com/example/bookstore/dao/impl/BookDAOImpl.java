package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.BookDAO;
import com.example.bookstore.pojo.Book;
import com.example.myssm.basedao.BaseDAO;

import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book");
    }

    @Override
    public Book getBookById(Integer bookId) {
        return load("select * from t_book where id = ?", bookId);
    }
}

package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDAO;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookDAO.getBookById(bookId);
    }

}

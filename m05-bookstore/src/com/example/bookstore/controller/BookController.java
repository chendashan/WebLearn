package com.example.bookstore.controller;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {

    private BookService bookService;

    public String index(HttpSession session) {
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList", bookList);
        return "index";
    }
}

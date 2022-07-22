package com.example.service.impl;

import com.example.controller.Code;
import com.example.dao.BookDao;
import com.example.domain.Book;
import com.example.exception.BusinessException;
import com.example.exception.SystemException;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public boolean save(Book book) {
        return bookDao.save(book) > 0;
    }

    @Override
    public boolean update(Book book) {
        return bookDao.update(book) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.delete(id) > 0;
    }

    @Override
    public Book getById(Integer id) {

        if (id < 0) {
            throw new BusinessException(Code.BUSINESS_ERR, "你的输入有问题仔细看看");
        }

        try {
            if (id == 1) {
                int i = 1 / 0;
            }
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "访问超时，先别这样操作了", e);
        }

        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}

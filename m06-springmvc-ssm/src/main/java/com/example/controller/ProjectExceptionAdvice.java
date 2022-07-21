package com.example.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        System.out.println("-----发现异常------");
        return new Result(4000, null,"出现异常");
    }
}

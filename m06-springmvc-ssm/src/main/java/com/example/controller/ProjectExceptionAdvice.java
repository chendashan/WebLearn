package com.example.controller;

import com.example.exception.BusinessException;
import com.example.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException exception) {
        //记录日志
        //发送消息给运营
        //发送邮件给开发人员，exception对象发给开发人员
        return new Result(exception.getCode(), null, exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException exception) {

        return new Result(exception.getCode(), null, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        System.out.println("-----发现异常------");
        //记录日志
        //发送消息给运营
        //发送邮件给开发人员，exception对象发给开发人员
        return new Result(Code.SYSTEM_UNKNOWN_ERR, null, "出现异常");
    }
}

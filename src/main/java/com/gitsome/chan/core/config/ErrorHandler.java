package com.gitsome.chan.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*
 * Created by Administrator on 2017/11/22.
 */

@ControllerAdvice
public class ErrorHandler {
    @Autowired
    private HttpServletRequest servletRequest;

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionVo errorResponse(Exception e) {
        ExceptionVo exception = new ExceptionVo();
        exception.setCode("ERROR");
        exception.setMessage(e.getMessage());
        exception.setDetail(e.getCause());
        exception.setServerTime(new Date());
        exception.setHost(servletRequest.getServerName());
        exception.setRequestId(servletRequest.getRequestedSessionId());
        return exception;
    }
}

package com.example.shiro.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String ErrorHandler(Exception e) {
        log.error("Permission verification failed ！", e);
        return "Permission verification failed ！";
    }
}
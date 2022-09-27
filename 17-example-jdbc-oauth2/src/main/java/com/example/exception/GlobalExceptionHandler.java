package com.example.exception;

import com.example.dto.common.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public ApiResp exceptionHandler(HttpServletRequest req, AccessDeniedException e) {
        log.error("不允许访问！原因是:", e.getMessage());
        return ApiResp.failed(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ApiResp handleException() {
        return ApiResp.failed("Exception Deal!");
    }
}
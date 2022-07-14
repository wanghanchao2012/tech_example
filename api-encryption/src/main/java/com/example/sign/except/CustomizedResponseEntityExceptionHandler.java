package com.example.sign.except;

import com.example.sign.domain.ResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseModel<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseModel exceptionResponse = new ResponseModel("-10000", ex.getMessage(), "");
        return exceptionResponse;
    }

    @ExceptionHandler(ServiceException.class)
    public final ResponseModel<Object> handleUserNotFoundExceptions(ServiceException ex, WebRequest request) {
        ResponseModel exceptionResponse = new ResponseModel("-10001", ex.getMessage(), "");
        return exceptionResponse;
    }
}
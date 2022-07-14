package com.example.sign.except;

public class ServiceException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = "-2001";
    }
}

package com.example.dto.common;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class ApiResp<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;
    private Map<String, Object> extra;


    public ApiResp(boolean success, String message) {
        this.success = success;
        this.code = "200";
        this.message = message;
    }

    public ApiResp(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static ApiResp success(String message) {
        return new ApiResp(true, message);
    }

    public static ApiResp success() {
        return new ApiResp(true, "success");
    }

    public static ApiResp<Object> failed() {
        return new ApiResp(false, "500", "failure");
    }

    public static ApiResp<Object> failed(Exception exception) {
        return new ApiResp(false, "500", exception == null ? "failure" : exception.getLocalizedMessage());
    }

    public static ApiResp<Object> failed(String exception) {
        return new ApiResp(false, "500", exception);
    }
}
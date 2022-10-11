package com.example.common;

import lombok.Data;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Data
@ToString
public class ApiResp<T> {
    private boolean success;
    private Integer code;
    private String message;
    private T data;
    private Map<String, Object> extra;

    public ApiResp() {
    }

    public ApiResp(boolean success, String message) {
        this.success = success;
        this.code = HttpServletResponse.SC_OK;
        this.message = message;
    }

    public ApiResp(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static ApiResp success(String message) {
        return new ApiResp(true, message);
    }

    public static <T> ApiResp<T> success(T data) {
        ApiResp apiResp = new ApiResp<>();
        apiResp.setCode(200);
        apiResp.setMessage("success");
        apiResp.setData(data);
        apiResp.setSuccess(true);
        return apiResp;
    }

    public static ApiResp success() {
        return new ApiResp(true, "success");
    }

    public static ApiResp<Object> failed() {
        return new ApiResp(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "failure");
    }

    public static ApiResp<Object> failed(Exception exception) {
        return new ApiResp(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception == null ? "failure" : exception.getLocalizedMessage());
    }


    public static <T> ApiResp<T> failed(String exception) {
        return new ApiResp(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception);
    }

    public static <T> ApiResp<T> failed(Integer code, String exception) {
        return new ApiResp(false, code, exception);
    }
}
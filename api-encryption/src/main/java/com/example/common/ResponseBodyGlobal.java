package com.example.common;

import com.example.sign.domain.ResponseModel;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseBodyGlobal implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {//就是这里处理返回签名数据
        if (o instanceof ResponseModel) {
            ResponseModel responseModel = (ResponseModel) o;
            //responseModel.setTimestamp(System.currentTimeMillis());
            return responseModel;
        }
        return o;
    }
}
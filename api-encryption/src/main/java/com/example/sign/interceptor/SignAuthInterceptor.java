package com.example.sign.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.sign.except.ServiceException;
import com.example.common.RequestWrapper;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wanghanchao
 */
public class SignAuthInterceptor implements HandlerInterceptor {
    /**
     * 时间戳请求最小限制(600s)
     * 设置的越小，安全系数越高，但是要注意一定的容错性
     */
    private static final long MAX_REQUEST = 10 * 60 * 1000L;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getBean() instanceof BasicErrorController) {
                return true;
            }
            if ("POST".equals(request.getMethod())) {
                RequestWrapper myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
                ValidateResp validateResp = checking(myRequestWrapper.getBody());
                if (!validateResp.isValidate()) {
                    throw validateResp.getException();
                }
            } else if ("GET".equals(request.getMethod())) {
                // do pass
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    private ValidateResp checking(String requestBody) {
        try {
            JSONObject reqMap = JSONUtil.parseObj(requestBody);
            String timestamp = reqMap.containsKey("timestamp") ? reqMap.get("timestamp").toString() : null;
            String sign = reqMap.containsKey("sign") ? reqMap.get("sign").toString() : null;

            if (StrUtil.isEmptyIfStr(timestamp)) {
                return new ValidateResp(false, new ServiceException(1003, "timestamp is required!"));
            } else if (StrUtil.isEmptyIfStr(sign)) {
                return new ValidateResp(false, new ServiceException(1004, "sign is required!"));
            }
            long now = System.currentTimeMillis();
            long time = Long.parseLong(timestamp);
            if (now - time > MAX_REQUEST) {
                return new ValidateResp(false, new ServiceException(1005, "time is expired!"));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ValidateResp(true, null);
    }

    /**
     * 校验返回对象
     */
    private static class ValidateResp {
        private boolean validate;
        private ServiceException exception;

        public ValidateResp(boolean validate, ServiceException exception) {
            this.validate = validate;
            this.exception = exception;
        }

        public boolean isValidate() {
            return validate;
        }

        public Exception getException() {
            return exception;
        }
    }
}
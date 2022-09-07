package com.example.aop.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 注解切面，用于拦截@AspectLogAop
 */
@Slf4j
@Aspect
@Component
public class AspectLogAop {

    @Pointcut("@annotation(com.example.aop.annotations.AspectLog)")
    public void cut() {
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String[] parameterNames = signature.getParameterNames();
        Map<String, Object> paramNameValueMap = new HashMap<>();
        paramNameValueMap.put("method", request.getMethod());
        for (int i = 0; i < parameterNames.length; i++) {
            paramNameValueMap.put(parameterNames[i], args[i]);
        }
        String logStr = paramNameValueMap.entrySet()
                .stream()
                .map(entry -> (entry.getKey()) + (":") + (entry.getValue()))
                .collect(Collectors.joining(","));
        log.info(logStr);
        return jp.proceed();
    }

}
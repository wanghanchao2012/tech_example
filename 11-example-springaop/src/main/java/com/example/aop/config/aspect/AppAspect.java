
package com.example.aop.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
//@Aspect
//@Component
public class AppAspect {
    //切入点：待增强的方法
    @Pointcut("execution(public * com.example.aop.controller.*.*(..))")
    //切入点签名
    public void log() {
        log.info("切入点。。。");
    }

    //前置通知
    @Before("log()")
    public void deBefore(JoinPoint jp) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("CLASS_METHOD : " + jp);
        log.info("ARGS : " + Arrays.toString(jp.getArgs()));

    }

    @Around("log()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //前置
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //后置
        long time = System.currentTimeMillis() - startTime;
        log.info("运行时长：" + time);
        return result;
    }

    //返回通知
    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("返回通知：方法的返回值 : " + ret);
    }

    //异常通知
    @AfterThrowing(throwing = "ex", pointcut = "log()")
    public void throwss(JoinPoint jp, Exception ex) {
        log.info("异常通知：方法异常时执行.....");
        log.info("产生异常的方法：" + jp);
        log.info("异常种类：" + ex);
    }

    //后置通知
    @After("log()")
    public void after(JoinPoint jp) {
        log.info("后置通知：最后且一定执行.....");
    }
} 

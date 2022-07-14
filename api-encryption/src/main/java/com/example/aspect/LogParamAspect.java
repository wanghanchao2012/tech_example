package com.example.aspect;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;


@Component
@Aspect
@Order(-1)
@Slf4j
public class LogParamAspect {

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在controller包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut(value = "execution(public * com.example.sign.controller..*.*(..))")
    public void webLog() {
    }


    private static String[] types = {"java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"};

    @Before(value = "webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String logStr = "";
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();
            // 记录下请求内容
            logStr += "URL:" + request.getRequestURI().toString() + " | ";
            logStr += "CLASS_METHOD:" + joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName() + " | ";
            logStr += "ARGS:";
            // joinPoint获取参数名
            String[] params = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();
            // joinPoint获取参数值
            Object[] args = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            // 打印请求参数
            int i = 0;
            for (Object arg : args) {
                if (arg == request || arg == response) {
                    i += 1;
                    continue;
                }
                String typeName = "";
                try {
                    typeName = arg.getClass().getTypeName();
                } catch (Exception e) {
                }
                if (!Arrays.asList(types).contains(typeName)) {
                    // 把参数转成json格式
                    logStr += "&" + params[i] + "=" + JSONObject.toJSONString(arg);
                } else {
                    logStr += "&" + params[i] + "=" + arg;
                }
                i += 1;
            }
            logStr += " | ";
            log.info(logStr);
        } catch (Throwable e) {
            log.error("HaLogParamAspect error.", e);
        }

    }
}
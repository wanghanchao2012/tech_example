package com.example.aop.annotations;


import java.lang.annotation.*;

/**
 * 切面日志注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AspectLog {


}
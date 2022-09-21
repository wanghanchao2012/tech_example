package com.example.multids.dynamic;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDatasourceAspect {

    @Pointcut("@annotation(dsScope)")
    public void dynamicPointcut(DsScope dsScope) {
    }

    @Around(value = "dynamicPointcut(dsScope)")
    public Object dynamicAround(ProceedingJoinPoint joinPoint, DsScope dsScope) throws Throwable {
        String scope = dsScope.scope();
        DataSourceType.setDataBaseType(scope);
        return joinPoint.proceed();
    }

    @After(value = "dynamicPointcut(dsScope)")
    public void after(DsScope dsScope) throws Throwable {
        DataSourceType.clearDataBaseType();
    }


}
 
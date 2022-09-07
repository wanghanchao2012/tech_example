package com.example.beanpost.processor;

import com.example.beanpost.dao.impl.EmployeeDAOImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof EmployeeDAOImpl) {
            System.out.println("Called postProcessBeforeInitialization() for :" + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof EmployeeDAOImpl) {
            System.out.println("Called postProcessAfterInitialization() for :" + beanName);
        }
        return bean;
    }
}
package com.example.config;

import com.example.importc.dto.PoolBean;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(PoolBean.class.getName());
        MutablePropertyValues property = beanDefinition.getPropertyValues();
        property.addPropertyValue("redisHost", "localhost");
        property.addPropertyValue("redisPort", "6377");
        //这里注册bean
        registry.registerBeanDefinition("poolBean", beanDefinition);
    }
}
package com.example.sign.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public com.example.sign.config.RequestCachingFilter requestCachingFilter() {
        return new com.example.sign.config.RequestCachingFilter();
    }

    @Bean
    public FilterRegistrationBean requestCachingFilterRegistration(
            com.example.sign.config.RequestCachingFilter requestCachingFilter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(requestCachingFilter);
        bean.setOrder(1);
        return bean;
    }
}

package com.example.sign.config;

import com.example.sign.interceptor.SignAuthInterceptor;
import com.example.filters.HttpServletRequestReplacedFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandlerInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type", "x-requested-with", "X-Custom-Header")
                .allowedMethods("PUT", "POST", "GET", "DELETE", "OPTIONS")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    @Bean
    public FilterRegistrationBean repeatedlyReadFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        HttpServletRequestReplacedFilter repeatedlyReadFilter = new HttpServletRequestReplacedFilter();
        registration.setFilter(repeatedlyReadFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public HandlerInterceptor getHandlerInterceptor() {
        return new SignAuthInterceptor();
    }
}
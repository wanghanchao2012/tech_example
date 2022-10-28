package com.example.config;

import com.example.importc.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    @Bean
    public Customer getBean() {
        Customer bean = new Customer();
        bean.setUserName("server1");
        bean.setPassword("password1");
        return bean;
    }
}
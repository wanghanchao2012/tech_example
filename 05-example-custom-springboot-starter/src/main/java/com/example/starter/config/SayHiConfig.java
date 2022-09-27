package com.example.starter.config;


import com.example.starter.properties.SayHiProperties;
import com.example.starter.service.SayHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SayHiProperties.class)
public class SayHiConfig {
    @Autowired
    private SayHiProperties properties;

    @Bean
    public SayHiService sayHiService() {
        return new SayHiService(properties.getAge(), properties.getName(), properties.getAddress());
    }
}
package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Student studentBean(@Value("${student.id:0}") Integer id,
                               @Value("${student.name:zhangsan}") String name) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        return student;
    }
}
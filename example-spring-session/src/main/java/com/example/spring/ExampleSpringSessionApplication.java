package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class ExampleSpringSessionApplication {

    public static void main(String[] args) {
        System.out.println("Hello Application!!!");
        SpringApplication.run(ExampleSpringSessionApplication.class, args);
    }

}

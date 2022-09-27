package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:info.properties"}, encoding = "UTF-8")
@SpringBootApplication
public class DemoSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootStarterApplication.class, args);
    }

}

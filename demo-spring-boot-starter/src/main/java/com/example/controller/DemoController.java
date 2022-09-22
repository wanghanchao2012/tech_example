package com.example.aop.controller;


import com.example.starter.service.SayHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {
    @Autowired
    private SayHiService sayHiService;

    @GetMapping("/say")
    public String sayWhat() {
        return sayHiService.hi();
    }

}
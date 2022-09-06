package com.example.shiro.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping("/delete")
    public String admin() {
        return "delete success!";
    }

    @GetMapping("/update")
    public String update() {
        return "update success!";
    }

    @GetMapping("/add")
    public String add() {
        return "add success!";
    }
}
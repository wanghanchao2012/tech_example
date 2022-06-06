package com.example.spring.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * SessionShareController <br>
 * 〈session共享控制器〉
 */
@RestController
@RequestMapping(value = "/session")
public class SessionShareController {

    @Value("${server.port}")
    Integer port;


    @GetMapping(value = "/set")
    public String set(HttpServletRequest httpRequest) {

        HttpSession session = httpRequest.getSession(true);
        System.out.println("test====>>xxxxxxxxxx");
        System.out.println("1234567890");
        session.setAttribute("user", "123456");
        return String.valueOf(port);
    }

    @GetMapping(value = "get")
    public String get(HttpSession session) {
        return "用户:" + session.getAttribute("user") + ",端口:" + port;
    }
}

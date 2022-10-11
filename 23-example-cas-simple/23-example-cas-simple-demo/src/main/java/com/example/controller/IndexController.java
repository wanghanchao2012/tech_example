package com.example.controller;

import com.example.common.ApiResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

    @GetMapping("/index")
    public Object index(HttpServletRequest request) {
        return ApiResp.success("你好 index～～");
    }

}

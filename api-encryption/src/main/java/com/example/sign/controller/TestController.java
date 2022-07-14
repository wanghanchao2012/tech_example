package com.example.sign.controller;

import com.example.sign.domain.ResponseModel;
import com.example.sign.annotation.SignAuth;
import com.example.sign.domain.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/test/")
@RestController
public class TestController {
    @PostMapping("list")
    @SignAuth
    public ResponseModel<List<String>> test(@RequestBody UserInfo userInfo) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        return ResponseModel.<List<String>>builder().data(list).build();
    }
}

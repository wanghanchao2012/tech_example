package com.example.controller;

import com.example.entity.WarnMessageInfo;
import com.example.service.WarnMessagePublishImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
public class WarnMessageController {
    @Autowired
    WarnMessagePublishImpl publish;

    @GetMapping("/publish")
    public void publish() {
        publish.register(new WarnMessageInfo("财务报表异常", "2022-03-18"));
        publish.register(new WarnMessageInfo("业务报表异常", "2022-03-19"));
    }
}

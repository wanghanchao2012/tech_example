package com.example.controller;

import com.example.constant.SsoConstant;
import com.example.rpc.SsoUser;
import com.example.utils.SessionUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/oauth2")
public class CallbackController {

    @RequestMapping("/code")
    @GetMapping
    public String retrive(@RequestParam("code") String code) {
        System.out.println("code==>" + code);
        return code;
    }
}

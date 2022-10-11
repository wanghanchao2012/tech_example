package com.example.controller;

import com.example.common.ApiResp;
import com.example.model.User;
import com.example.service.AppService;
import com.example.service.UserService;
import com.example.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;

    @GetMapping("/login")
    public Object login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String appId = request.getParameter("appId");
        ApiResp<User> loginResp = userService.login(username, password, appId);
        if (loginResp.isSuccess()) {
            String token = JwtTokenUtil.getToken(loginResp.getData());
            return ApiResp.success(token);
        }
        return ApiResp.failed("用户名或密码错误");
    }

}

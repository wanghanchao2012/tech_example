package com.example.shiro.controller;


import com.example.shiro.beans.User;
import com.example.shiro.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/admin/")
public class AdminController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/delete")
    public String admin() {
        RedisUtils utils = new RedisUtils(stringRedisTemplate);
        boolean set = utils.set("sss", "abc");
        utils.expire("sss", 10);
        System.out.println(utils.get("sss"));
        System.out.println();
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
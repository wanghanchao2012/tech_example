package com.example.shiro.controller;


import com.example.shiro.beans.User;
import com.example.shiro.beans.common.ResponseResult;
import com.example.shiro.service.AuthService;
import com.example.shiro.service.LoginService;
import com.example.shiro.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public ResponseResult<String> login(User user, HttpServletResponse httpServletResponse) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResponseResult.fail("请输入用户名和密码！");
        }
        //用户认证信息
        /*Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );*/
        String token = null;
        try {
            User currentUser = loginService.getUserByName(user.getUserName());
            if (Objects.nonNull(currentUser) && Objects.equals(currentUser.getPassword(), user.getPassword())) {
                token = JWTUtil.signNotExpire(user.getUserName(), user.getPassword());
                httpServletResponse.setHeader("Authorization", token);
                //解决跨域请求时无法得到Authorization的问题
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                //subject.login(usernamePasswordToken);
                authService.saveToken(user.getUserName(), token);
                authService.refreshRequestTs(user.getUserName());
            } else {
                return ResponseResult.fail("账号或密码错误！");
            }
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return ResponseResult.fail("用户名不存在！");
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return ResponseResult.fail("账号或密码错误！");
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return ResponseResult.fail("没有权限");
        }
        return ResponseResult.success(token);
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }


    @GetMapping("/401")
    public String error() {
        return "request error!";
    }
}
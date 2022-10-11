package com.example.service.impl;

import com.example.common.ApiResp;
import com.example.model.User;
import com.example.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static List<User> userList;

    static {
        userList = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("123456");
        user.setName("管理员");
        user.setAppIdsOwn(Lists.newArrayList("server1", "demo1"));
        userList.add(user);
    }

    @Override
    public ApiResp<User> login(String username, String password, String appId) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return ApiResp.success(new User(user.getId(), user.getName(), user.getUsername(), user.getPassword(), appId));
                } else {
                    return ApiResp.<User>failed("密码有误");
                }
            }
        }
        return ApiResp.failed("用户不存在");
    }
}

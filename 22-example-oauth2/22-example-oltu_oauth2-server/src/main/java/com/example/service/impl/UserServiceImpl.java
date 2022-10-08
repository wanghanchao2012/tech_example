package com.example.service.impl;

import com.example.model.User;
import com.example.rpc.Result;
import com.example.rpc.SsoUser;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static List<User> userList;

    static {
        userList = new ArrayList<>();
        userList.add(new User(1, "管理员", "admin", "123456"));
    }

    @Override
    public Result<SsoUser> login(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return Result.createSuccess(new SsoUser(user.getId(), user.getUsername()));
                } else {
                    return Result.createError("密码有误");
                }
            }
        }
        return Result.createError("用户不存在");
    }
}

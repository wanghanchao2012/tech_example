package com.example.service;

import com.example.rpc.Result;
import com.example.rpc.SsoUser;

/**
 * 用户服务接口
 *
 * @author Joe
 */
public interface UserService {

    /**
     * 登录
     *
     * @param username 登录名
     * @param password 密码
     * @return
     */
    public Result<SsoUser> login(String username, String password);
}

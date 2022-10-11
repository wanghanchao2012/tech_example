package com.example.service;

import com.example.common.ApiResp;
import com.example.model.User;

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
    public ApiResp<User> login(String username, String password,String appId);
}

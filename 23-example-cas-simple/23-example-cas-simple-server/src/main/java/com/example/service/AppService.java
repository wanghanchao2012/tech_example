package com.example.service;

import com.example.common.ApiResp;

/**
 * 应用服务接口
 *
 * @author Joe
 */
public interface AppService {

    boolean exists(String appId);

    ApiResp<Object> validate(String appId, String appSecret);
}

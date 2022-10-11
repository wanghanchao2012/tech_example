package com.example.service.impl;

import com.example.common.ApiResp;
import com.example.model.App;
import com.example.service.AppService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("appService")
public class AppServiceImpl implements AppService {

    private static List<App> appList;

    static {
        appList = new ArrayList<>();
        appList.add(new App("服务端1", "server1", "123456"));
        appList.add(new App("客户端1", "demo1", "123456"));
    }

    @Override
    public boolean exists(String appId) {
        return appList.stream().anyMatch(app -> app.getAppId().equals(appId));
    }

    @Override
    public ApiResp<Object> validate(String appId, String appSecret) {
        for (App app : appList) {
            if (app.getAppId().equals(appId)) {
                if (app.getAppSecret().equals(appSecret)) {
                    return ApiResp.success();
                } else {
                    return ApiResp.failed("appSecret有误");
                }
            }
        }
        return ApiResp.failed("appId不存在");
    }
}

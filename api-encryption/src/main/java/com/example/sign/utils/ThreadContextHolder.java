package com.example.sign.utils;


import cn.hutool.system.UserInfo;

public class ThreadContextHolder {

    private ThreadContextHolder() {
    }

    private static ThreadLocal<UserInfo> userInfo = new ThreadLocal<>();

    public static void setUserInfo(UserInfo us) {
        userInfo.set(us);
    }

    public static UserInfo getUserInfo() {
        return userInfo.get();
    }

    public static void destroy() {
        if (userInfo != null) {
            userInfo.remove();
        }
    }
} 
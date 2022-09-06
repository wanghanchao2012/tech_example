package com.example.shiro.beans.common;

/**
 * 常量
 */
public class Constant {

    private Constant() {
    }

    /**
     * redis-OK
     */
    public static final String OK = "OK";

    public static final long EXRP_MINUTE = 60 * 60 * 24L;


    /**
     * redis-key-前缀-shiro:access_token:
     */
    public static final String PREFIX_SHIRO_ACCESS_TOKEN = "shiro:access_token:";

    public static final String PREFIX_SHIRO_ACCESS_TOKEN_VALUE = "shiro:access_token:value:";


    /**
     * redis-key-前缀-shiro:refresh_token:
     */
    public static final String PREFIX_SHIRO_REFRESH_TOKEN = "shiro:refresh_token:";


    // 过期时间5分钟 // 5 * 60 * 1000;
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1 * 60 * 1000;

}

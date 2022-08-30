package com.example.sign.util;

public class Constants {
    public static final String SIGN_TIME_STAMP = "_timeStamp";
    public static final String SIGN_HEADER = "X-SIGN";
    //超时时效，超过此时间认为签名过期
    public static final Long EXPIRE_TIME = 5 * 60 * 1000L;

}

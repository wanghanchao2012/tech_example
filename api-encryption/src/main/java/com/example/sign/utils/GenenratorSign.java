package com.example.sign.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class GenenratorSign {

    public static void main(String[] args) {
        String str = "data={\"age\":12}&name=zhangsan&timestamp=1657704362319&key=test";
        String s = DigestUtils.md5Hex(str).toUpperCase();
        System.out.println(s);
    }
}

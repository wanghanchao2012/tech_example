package com.example.token;

import cn.hutool.core.util.StrUtil;
import com.example.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalTokenManager {

    private static final Map<String, User> tokenMap = new ConcurrentHashMap<>();

    public static boolean put(String token, User user) {
        tokenMap.put(token, user);
        return true;
    }

    public static boolean remove(String token) {
        if (StrUtil.isNotBlank(token)) {
            tokenMap.remove(token);
        }
        return true;
    }
}

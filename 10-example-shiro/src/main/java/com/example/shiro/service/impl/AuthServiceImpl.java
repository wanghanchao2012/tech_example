package com.example.shiro.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.example.shiro.beans.common.Constant;
import com.example.shiro.service.AuthService;
import com.example.shiro.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    RedisUtils redisUtils;

    @Override
    public void refreshRequestTs(String username) {
        String cacheKey = Constant.PREFIX_SHIRO_ACCESS_TOKEN + username;
        String currentTime = String.valueOf(System.currentTimeMillis());
        //caching one hour
        redisUtils.set(cacheKey, currentTime, Constant.EXRP_MINUTE);
    }

    @Override
    public boolean tokenIsExpired(String username) {
        String cacheKey = Constant.PREFIX_SHIRO_ACCESS_TOKEN + username;
        long currentTs = System.currentTimeMillis();
        //caching one hour
        String cacheTime = redisUtils.exists(cacheKey) ? redisUtils.get(cacheKey) : null;
        if (StringUtils.isEmpty(cacheTime)) {
            log.info("first visit...");
            cacheTime = String.valueOf(currentTs);
        } else {
            Instant instant = Instant.ofEpochMilli(NumberUtil.parseLong(cacheTime));
            ZoneId zone = ZoneId.systemDefault();
            String lastRequestTime = LocalDateTime.ofInstant(instant, zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            log.info("last visit time : " + lastRequestTime);
        }
        log.info("currentTime:" + currentTs + ",cacheTime:" + cacheTime);
        //最后一次请求时间+过期时间 小于当前时间 说明最后一次访问距当前已经超过5分钟说明过期
        if (currentTs > NumberUtil.parseLong(cacheTime) + Constant.ACCESS_TOKEN_EXPIRE_TIME) {
            return true;
        }
        return false;
    }


    @Override
    public void saveToken(String username, String token) {
        String cacheKey = Constant.PREFIX_SHIRO_ACCESS_TOKEN_VALUE + username;
        //caching one hour
        redisUtils.set(cacheKey, token, Constant.EXRP_MINUTE);
    }

    @Override
    public String getTokenFromCache(String username) {
        String cacheKey = Constant.PREFIX_SHIRO_ACCESS_TOKEN_VALUE + username;
        //caching one hour
        return redisUtils.get(cacheKey);
    }
}

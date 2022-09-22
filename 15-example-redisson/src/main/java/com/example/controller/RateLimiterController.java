package com.example.controller;

import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    @Autowired
    private RedissonClient redissonClient;

    public void ss() {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("myRateLimiter");
// 初始化
// 最大流速 = 每1秒钟产生10个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 2, 5, RateIntervalUnit.SECONDS);

        rateLimiter.acquire();
        rateLimiter.acquire();
        rateLimiter.acquire();
        rateLimiter.acquire();
    }
}

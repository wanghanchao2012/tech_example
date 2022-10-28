package com.example.importc.dto;

import lombok.Data;

@Data
public class PoolBean {
    private String redisHost = "127.0.0.1";
    private String redisPort = "6379";
    private String redisDb = "0";
}
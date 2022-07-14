package com.example.sign.domain;

import lombok.Data;

import java.util.Map;

@Data
public class UserInfo {
    private String name;
    private String timestamp;
    private String sign;
    private Map<String,Object> data;
}

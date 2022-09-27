package com.example.spring.domain.entity;

import lombok.Data;

@Data
public class RspWrapper<T> {
    private int code;
    private String msg;
    private T data;
}

package com.example.multids.entity;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class User {

    private String id;
    private String account;
    private String password;
    private String username;
    private String reg_time;

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("account='" + account + "'")
                .add("password='" + password + "'")
                .add("username='" + username + "'")
                .add("reg_time='" + reg_time + "'")
                .toString();
    }
}

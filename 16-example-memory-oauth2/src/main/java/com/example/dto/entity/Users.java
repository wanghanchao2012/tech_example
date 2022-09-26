package com.example.dto.entity;

import lombok.Data;

@Data
public class Users {
    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * enabled
     */
    private int enabled;
}

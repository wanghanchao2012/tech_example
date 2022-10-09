package com.example.service;

import com.example.model.User;

public interface AccessTokenService {
    String generateAccessToken(User user);
}

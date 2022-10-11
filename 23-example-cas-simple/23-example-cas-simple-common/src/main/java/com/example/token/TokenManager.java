package com.example.token;

import com.example.model.User;

public interface TokenManager {

    boolean put(String token, User user);

    boolean remove(String token);
}

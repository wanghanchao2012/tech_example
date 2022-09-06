package com.example.shiro.service;

public interface AuthService {
    public String getTokenFromCache(String username);

    public void saveToken(String username, String token);

    public void refreshRequestTs(String username);

    public boolean tokenIsExpired(String username);
}

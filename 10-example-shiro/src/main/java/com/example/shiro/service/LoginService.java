package com.example.shiro.service;

import com.example.shiro.beans.User;

public interface LoginService {
    User getUserByName(String name);
}

package com.example.mapper;

import com.example.model.User;

import java.util.List;

public interface UserMapper {
    List<User> getUser(User user);

    Integer deleteUser(String username);

    Integer updateUser(User user);

    Integer insertUser(User user);
}

package com.example.aop.service;

import com.example.aop.dto.ReqAlbum;

public interface UserService {
    void print(String name, String author);

    void sayHi(String name, String author);

    void printAlbum(ReqAlbum reqAlbum);

}

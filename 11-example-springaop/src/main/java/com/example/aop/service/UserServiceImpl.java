package com.example.aop.service;

import com.example.aop.dto.ReqAlbum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void print(String name, String author) {
        log.info("print==>> song name : " + name + ", author : " + author);
    }

    @Override
    public void sayHi(String name, String author) {
        log.info("sayHi==>> song name : " + name + ", author : " + author);
    }

    @Override
    public void printAlbum(ReqAlbum reqAlbum) {
        log.info("sayHi json==>> song name : " + reqAlbum.getName() + ", author : " + reqAlbum.getAuthor());
    }
}

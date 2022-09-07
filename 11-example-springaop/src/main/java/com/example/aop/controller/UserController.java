package com.example.aop.controller;

import com.example.aop.annotations.AspectLog;
import com.example.aop.dto.ReqAlbum;
import com.example.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @AspectLog 
    @GetMapping("/print")
    public ResponseEntity<String> print(@RequestParam String name, @RequestParam String author) {
        userService.print(name, author);
        return ResponseEntity.ok("print invoke end...");
    }

    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam String name, @RequestParam String author) {
        userService.sayHi(name, author);
        return ResponseEntity.ok("sayHi invoke end...");
    }

    @GetMapping("/json")
    public ResponseEntity<String> Json(@RequestBody ReqAlbum reqAlbum) {
        userService.printAlbum(reqAlbum);
        return ResponseEntity.ok("json invoke end...");
    }
}

package com.example.sign.controller;

import com.example.sign.config.response.ResponseResult;
import com.example.sign.config.sign.Signature;
import com.example.sign.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class SignTestController {

    @Signature
    @PostMapping("test/signValid")
    public ResponseResult<String> myController(@RequestParam String client,
                                               @RequestParam String a,
                                               @RequestParam String b
            , @RequestBody User user) {
        return ResponseResult.success(String.join(",", client, user.toString()));
    }
 
}

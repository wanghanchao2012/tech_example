package com.example;

import com.example.server.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class CheckCenterController {

    //推送数据接口
    @ResponseBody
    @GetMapping("/socket/push")
    public String pushToWeb(@RequestParam(required = false) String uid, @RequestParam String message) {

        try {
            WebSocketServer.sendInfo(message, uid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

}
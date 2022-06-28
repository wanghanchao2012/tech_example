package com.example.sign.controller;

import cn.hutool.core.map.MapUtil;
import com.example.sign.config.response.ResponseResult;
import com.example.sign.config.sign.Signature;
import com.example.sign.entity.User;
import com.example.sign.util.ApiSignUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author pdai
 */
@RestController
@RequestMapping("user")
public class SignTestController {

    @Signature
    @PostMapping("test/{id}")
    public ResponseResult<String> myController(@PathVariable String id
            , @RequestParam String client
            , @RequestBody User user) {
        return ResponseResult.success(String.join(",", id, client, user.toString()));
    }

    @GetMapping("test/api")
    public ResponseResult<Map<String, Object>> myController() {
        String key = ApiSignUtils.DEFAULT_SECRET_KEY;
        Map<String, Object> signatureMap = ApiSignUtils.getSignature(MapUtil.builder()
                .put("name", "zhangsan")
                .put("age", 12).build(), key);
        signatureMap.entrySet().forEach(record -> {
            System.out.println("key:" + record.getKey() + ",value:" + record.getValue());
        });
        boolean b = ApiSignUtils.checkSignature(signatureMap, key);
        System.out.println(b);
        return ResponseResult.success(signatureMap);
    }
}

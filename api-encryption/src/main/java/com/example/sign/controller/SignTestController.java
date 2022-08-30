package com.example.sign.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.example.sign.config.response.ResponseResult;
import com.example.sign.config.sign.Signature;
import com.example.sign.entity.User;
import com.example.sign.util.Constants;
import com.example.sign.util.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("user")
public class SignTestController {

    @Signature
    @PostMapping("test/my")
    public ResponseResult<String> myController(@RequestParam String client
            , @RequestBody User user) {
        return ResponseResult.success(String.join(",", client, user.toString()));
    }

    @GetMapping("test/generator")
    public ResponseResult<Map<Object, Object>> generator(@RequestParam(required = false) String client) {
        return ResponseResult.success(getSignature(null));
    }

    @GetMapping("test/generator/t")
    public ResponseResult<Map<Object, Object>> generator(@RequestParam String client, @RequestParam String _t) {
        return ResponseResult.success(getSignature(_t));
    }

    public Map<Object, Object> getSignature(String _t) {
        LinkedHashMap<Object, Object> signatureMap = new LinkedHashMap<>();
        signatureMap.put("name", "zhangsan");
        signatureMap.put("age", 12);
        signatureMap.put(Constants.SIGN_TIME_STAMP, StringUtils.isBlank(_t) ? String.valueOf(System.currentTimeMillis()) : _t);
        String toJsonStr = JSONUtil.toJsonStr(signatureMap);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Map<String, Object> requestParamMap = WebUtils.getParametersStartingWith(request, "");
        Map<String, String[]> params = new HashMap<>();
        for (Map.Entry<String, Object> entry : requestParamMap.entrySet()) {
            if (entry.getValue() instanceof String[]) {
                params.put(entry.getKey(), (String[]) entry.getValue());
            } else {
                params.put(entry.getKey(), new String[]{String.valueOf(entry.getValue())});
            }
        }
        SignUtil.sign(toJsonStr, params);
        signatureMap.put("sign", SignUtil.sign(toJsonStr, params));
        return signatureMap;
    }

    public static void main(String[] args) {
        System.out.println(getSignatureTest());
    }

    public static TreeMap<String, Object> getSignatureTest() {
        TreeMap<String, Object> signatureMap = new TreeMap<>();
        signatureMap.put("name", "zhangsan");
        signatureMap.put("age", 12);
        signatureMap.put("timeStamp", System.currentTimeMillis());
        String toJsonStr = JSONUtil.toJsonStr(signatureMap);
        TreeMap<String, Object> requestParamMap = new TreeMap();
        requestParamMap.put("client", "xiaomi");
        Map<String, String[]> params = new HashMap<>();
        for (Map.Entry<String, Object> entry : requestParamMap.entrySet()) {
            if (entry.getValue() instanceof String[]) {
                params.put(entry.getKey(), (String[]) entry.getValue());
            } else {
                params.put(entry.getKey(), new String[]{String.valueOf(entry.getValue())});
            }
        }
        signatureMap.put("sign", SignUtil.sign(toJsonStr, params));
        return signatureMap;
    }
}

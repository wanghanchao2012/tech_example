package com.example.sign.util;

import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SignExample {

    public static void main(String[] args) {
        Map<Object, Object> signature = getSignature("1661846732596");
        signature.entrySet().forEach(s -> {
            System.out.println(s.getKey() + "==" + s.getValue());
        });
    }

    public static Map<Object, Object> getSignature(String _t) {
        LinkedHashMap<Object, Object> signatureMap = new LinkedHashMap<>();
        signatureMap.put("name", "zhangsan");
        signatureMap.put("age", 12);
        signatureMap.put(Constants.SIGN_TIME_STAMP, StringUtils.isBlank(_t) ? String.valueOf(System.currentTimeMillis()) : _t);
        String toJsonStr = JSONUtil.toJsonStr(signatureMap);
        Map<String, Object> requestParamMap = new LinkedHashMap<>();
        requestParamMap.put("client", "xiaomi");
        requestParamMap.put("a", "1");
        requestParamMap.put("b", "2");
        Map<String, String[]> params = new LinkedHashMap<>();
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

}

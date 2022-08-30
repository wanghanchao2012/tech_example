package com.example.sign.util;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.security.SignatureException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SignUtil {

    private static final String DEFAULT_SECRET = "2382392090*&^%$#@!";

    public static String sign(String body, Map<String, String[]> params, String[] paths) {
        StringBuilder sb = new StringBuilder();
        if (CharSequenceUtil.isNotBlank(body)) {
            sb.append(body).append('#');
        }

        if (!CollectionUtils.isEmpty(params)) {
            params.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(paramEntry -> {
                        String paramValue = String.join(",", Arrays.stream(paramEntry.getValue()).sorted().toArray(String[]::new));
                        sb.append(paramEntry.getKey()).append("=").append(paramValue).append('#');
                    });
        }

        if (ArrayUtil.isNotEmpty(paths)) {
            String pathValues = String.join(",", Arrays.stream(paths).sorted().toArray(String[]::new));
            sb.append(pathValues);
        }
        return SecureUtil.sha256(String.join("#", DEFAULT_SECRET, sb.toString()));
    }

    public static String sign(String body, Map<String, String[]> params) {
        StringBuilder sb = new StringBuilder();
        if (CharSequenceUtil.isNotBlank(body)) {
            sb.append(body).append('#');
        }
        if (params == null) {
            params = new HashMap<>();
        }
        if (!params.containsKey(Constants.SIGN_TIME_STAMP)) {
            params.put(Constants.SIGN_TIME_STAMP, new String[]{System.currentTimeMillis() + ""});
        }
        params.entrySet()
                .stream()
                .filter(e -> !e.getKey().startsWith("_"))
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(paramEntry -> {
                    String paramValue = String.join(",", Arrays.stream(paramEntry.getValue()).sorted().toArray(String[]::new));
                    sb.append(paramEntry.getKey()).append("=").append(paramValue).append('#');
                });
        try {
            log.info("generator sign source info :" + sb.toString());
            return HmacSHA1.calculateRFC2104HMAC(sb.toString(), DEFAULT_SECRET);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }
}

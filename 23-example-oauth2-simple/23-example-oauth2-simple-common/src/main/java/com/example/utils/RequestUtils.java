package com.example.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.common.ApiResp;
import com.example.common.CasAppInfo;
import com.example.common.Constants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class RequestUtils {
    //5分钟时效
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 5 * 60 * 1000;

    public static String getTokenWithRequest(HttpServletRequest request) {
        String token = request.getHeader(Constants.HEADER_TOKEN_NAME);
        return token;
    }

    public static ApiResp<String> validator(String token, CasAppInfo casAppInfo) {
        if (StrUtil.isBlank(token)) {
            return ApiResp.failed("token不能为空");
        }
        try {
            JwtTokenUtil.verity(token);
        } catch (JWTDecodeException e) {
            return ApiResp.failed(HttpServletResponse.SC_BAD_GATEWAY, "token格式无效");
        }
        Map<String, Object> paramMap = JwtTokenUtil.verityAsMap(token);
        long loginTime = NumberUtil.parseLong(paramMap.get("loginTime").toString());
        String username = paramMap.get("username").toString();
        String name = paramMap.get("name").toString();
        String id = paramMap.get("id").toString();
        if (isExpired(loginTime)) {
            return ApiResp.failed("token过期");
        }
        if (!Objects.equals(username, casAppInfo.getAppId())) {
            return ApiResp.failed("appId不匹配");
        }
        return ApiResp.success();
    }

    public static boolean isExpired(Long loginTime) {
        long currentTs = System.currentTimeMillis();
        try {
            if (currentTs > loginTime + ACCESS_TOKEN_EXPIRE_TIME) {
                return true;
            }
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isExpired(String token) {
        if (StringUtils.isEmpty(token)) {
            return true;
        }
        Map<String, Object> paramMap = JwtTokenUtil.verityAsMap(token);
        long loginTime = NumberUtil.parseLong(paramMap.get("loginTime").toString());
        return isExpired(loginTime);
    }
}

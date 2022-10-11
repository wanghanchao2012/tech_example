package com.example.config;

import cn.hutool.json.JSONUtil;
import com.example.common.ApiResp;
import com.example.common.CasAppInfo;
import com.example.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter extends IOAuth2Filter {
    private CasAppInfo casAppInfo;

    public LoginFilter(CasAppInfo casAppInfo) {
        this.casAppInfo = casAppInfo;
    }

    @Override
    public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = RequestUtils.getTokenWithRequest(request);
        ApiResp<String> validator = RequestUtils.validator(token, casAppInfo);
        if (!validator.isSuccess()) {
            responseJson(response, validator);
            return false;
        }
        return true;
    }

    protected void responseJson(HttpServletResponse response, ApiResp<String> validator) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.toJsonStr(validator));
        writer.flush();
        writer.close();
    }
}

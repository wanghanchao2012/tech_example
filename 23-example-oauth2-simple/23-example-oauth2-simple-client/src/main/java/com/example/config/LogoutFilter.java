package com.example.config;

import cn.hutool.json.JSONUtil;
import com.example.common.ApiResp;
import com.example.token.LocalTokenManager;
import com.example.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class LogoutFilter extends IOAuth2Filter {
    @Override
    public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if (Objects.equals("logout", path)) {
            String token = RequestUtils.getTokenWithRequest(request);
            LocalTokenManager.remove(token);
            responseJson(response, ApiResp.success("登出成功"));
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

package com.example.controller;

import cn.hutool.core.util.StrUtil;
import com.example.common.ApiResp;
import com.example.service.AppService;
import com.example.service.UserService;
import com.example.utils.RequestUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * callback 得到授权码的模式适用于为第三方的服务提供资源，本次仅仅是单点登录的实现，所以可略去该步骤
 */
@RestController
public class AuthorizeController {
    @Autowired
    AppService appService;
    @Autowired
    UserService userService;

    @RequestMapping("authorize")
    public Object authorize(HttpServletRequest request) {
        try {
            OAuthAuthzRequest oAuthAuthzRequest = new OAuthAuthzRequest(request);
            if (!appService.exists(oAuthAuthzRequest.getClientId())) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_GATEWAY)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription("客户端验证失败，如错误的client_id/client_secret。")
                                .buildJSONMessage();
                return ApiResp.failed(response.getResponseStatus(), response.getBody());
            }

            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token) || RequestUtils.isExpired(token)) {
                return ApiResp.failed("请登录");
            }

            String responseType = oAuthAuthzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            String authCode = null;
            if (Objects.equals(responseType, ResponseType.CODE.toString())) {
                OAuthIssuerImpl oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
                authCode = oAuthIssuer.authorizationCode();
                System.out.println(authCode);
            }


        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
        return null;
    }


}
package com.example.service.impl;

import com.example.model.User;
import com.example.service.AccessTokenService;
import com.example.utils.JwtTokenUtil;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    public static String getAccessToken() {
        //生成Access Token
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String accessToken = null;
        try {
            accessToken = oauthIssuerImpl.accessToken();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    @Override
    public String generateAccessToken(User user) {
        String token = JwtTokenUtil.getToken(user);
        return token;
    }
}

package com.example.utils;

import cn.hutool.core.map.MapUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.model.User;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    private static final String secret = "839883498$*&(*@";

    public static String getToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim("username", user.getUsername())
                    .withClaim("name", user.getName())
                    .withClaim("id", user.getId())
                    .withClaim("appId", user.getAppId())
                    .withClaim("loginTime", System.currentTimeMillis())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public static Map<String, Claim> verity(String token) throws JWTDecodeException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        //token第二部分得到的参与生成token的键值对
        Map<String, Claim> claims = jwt.getClaims();
        return claims;
    }

    public static Map<String, Object> verityAsMap(String token) throws JWTDecodeException {
        Map<String, Claim> claims = verity(token);
        Map<String, Object> claimsAsMap = new HashMap<>();
        if (MapUtil.isNotEmpty(claims)) {
            claims.entrySet().forEach(record -> {
                if (!record.getValue().isMissing()) {
                    Object value = record.getValue().as(Object.class);
                    claimsAsMap.put(record.getKey(), value);
                }
            });
        }
        return claimsAsMap;
    }

}

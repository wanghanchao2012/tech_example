package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public class HS256Token {

    /**
     * JWT分为三部分
     * Header（头部）// Header 部分是一个 JSON 对象，描述 JWT 的元数据，通常是下面的样子。
     * Payload（负载）// Payload 部分是一个 JSON 对象，用来存放实际需要传递的数据
     * Signature（签名）// Signature 部分是对前两部分的签名，防止数据篡改
     */

    private static final String secret = "secret123456";

    public static void main(String[] args) {
        String token = getToken();
        Map<String, Claim> verity = verity(token);
        System.out.println("username:" + verity.get("username").asString());
    }

    private static String getToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim("username", "melody")
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
        }
        return null;
    }


    private static Map<String, Claim> verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            //token第二部分得到的参与生成token的键值对
            Map<String, Claim> claims = jwt.getClaims();
            return claims;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            exception.printStackTrace();
        }
        return null;
    }
}

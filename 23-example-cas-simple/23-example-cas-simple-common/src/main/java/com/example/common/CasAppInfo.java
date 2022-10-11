package com.example.common;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@Component
public class CasAppInfo {
    @Value("${sso.app.secret:''}")
    private String appSecret;
    @Value("${sso.server.url:''}")
    private String serverUrl;
    @Value("${sso.app.id:''}")
    private String appId;
}
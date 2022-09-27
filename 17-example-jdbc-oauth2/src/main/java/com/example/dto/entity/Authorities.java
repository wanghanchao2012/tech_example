package com.example.dto.entity;

import lombok.Data;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;

@Data
public class Authorities {
    private String username;
    private String authority;
}

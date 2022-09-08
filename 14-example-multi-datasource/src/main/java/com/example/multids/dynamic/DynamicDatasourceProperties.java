package com.example.multids.dynamic;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "spring.dynamic")
@Data
public class DynamicDatasourceProperties {
    private Map<String, Map<String, String>> datasource;
} 
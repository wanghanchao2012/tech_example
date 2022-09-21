package com.example.multids.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DynamicDatasourceProperties {
    private Map<String, Map<String, String>> druid;
    private String type;
    private DruidDataSource poolConfig;
} 
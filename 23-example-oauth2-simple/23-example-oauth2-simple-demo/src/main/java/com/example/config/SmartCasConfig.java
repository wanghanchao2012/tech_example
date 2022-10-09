package com.example.config;

import com.example.common.CasAppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartCasConfig {
    @Autowired
    CasAppInfo casAppInfo;


    /**
     * 单点登录Filter容器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<SmartFilter> smartContainer() {
        SmartFilter smartContainer = new SmartFilter();
        smartContainer.setFilters(new LogoutFilter(), new LoginFilter(casAppInfo));

        FilterRegistrationBean<SmartFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(smartContainer);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        registration.setName("smartContainer");
        return registration;
    }
}

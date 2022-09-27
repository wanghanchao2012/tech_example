package com.example.spring.config;


import org.assertj.core.util.Lists;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * 在链接上携带区域信息
 */

public class MyLocaleResolver implements LocaleResolver {

    private static List<String> langType = Lists.newArrayList("en_US", "zh_CN", "zh_TW");

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("accept-language");
        if (StringUtils.hasLength(acceptLanguage)) {
            Optional<String> languageOpt = langType.stream().filter(acceptLanguage::contains).findFirst();
            if (languageOpt.isPresent()) {
                acceptLanguage = languageOpt.get();
            }
        } else {
            acceptLanguage = request.getParameter("l");
        }
        Locale locale = Locale.getDefault();
        if (StringUtils.hasLength(acceptLanguage) && acceptLanguage.contains("_")) {
            String[] split = acceptLanguage.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
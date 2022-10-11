package com.example.http;


import java.util.Map;


public interface HttpServletRequest {
    /**
     * 获取请求参数
     *
     * @param key 请求参数
     * @return 请求参数的值
     */
    String getParameter(String key);

    /**
     * 设置请求编码
     *
     * @param charset 编码类型
     */
    void setCharacter(String charset);

    /**
     * 获取请求头参数
     *
     * @param key
     * @return
     */
    String getHeadField(String key);

    /**
     * 获取请求ip
     */
    String getRequestIP();

    /**
     * 获取所有请求参数
     *
     * @return
     */
    Map<String, String> getParameters();
}

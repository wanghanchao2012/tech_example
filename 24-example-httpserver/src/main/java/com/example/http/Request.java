package com.example.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;


/**
 * 采用适配器原理将exange对象拆分成Request和Response两个对象
 *
 * @author wf
 */
public class Request implements HttpServletRequest {
    //定义一个Exange对象
    private HttpExchange exchange;

    /**
     * 保存字符串编码
     */
    private String charset = "utf-8";

    /**
     * 请求参数保存
     */
    private Map<String, String> query = null;

    /**
     * 参数部分字符串
     */
    private String paramesString = "";

    /**
     * 保存当前访问对象的cookie
     */
    private HttpCookie cookie;

    /**
     * 构造方法
     *
     * @param exchange 每次请求的exange对象
     */
    public Request(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String getParameter(String key) {
        if (query == null) {
            query = queryFormat();
        }
        return query.get(key);
    }

    @Override
    public Map<String, String> getParameters() {
        if (query == null) {
            query = queryFormat();
        }
        return query;
    }

    @Override
    public void setCharacter(String charset) {
        this.charset = charset;
        query = null;
        queryFormat();
    }

    @Override
    public String getHeadField(String key) {
        Headers header = exchange.getRequestHeaders();
        return (header.get(key) != null ? header.get(key).toString() : null);
    }


    @Override
    public String getRequestIP() {
        return exchange.getRemoteAddress().getHostName();
    }

    /**
     * 请求参数拆分
     */
    private Map<String, String> queryFormat() {
        if (paramesString == null || "".equals(paramesString)) {
            paramesString = queryGet();
        }
        return getQuerys();
    }

    /**
     * 请求参数拆分
     */
    private String queryGet() {
        //获取请求的地址
        String urlString = exchange.getRequestURI().toString();

        //拿到输出流对象
        OutputStream out = null;

        BufferedReader read = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        String line = "";
        urlString = urlString.contains("?") ? urlString + "&" : urlString + "?";

        //读取POst请求参数
        try {
            while ((line = read.readLine()) != null) {
                urlString += line;
            }
        } catch (Exception e) {
            System.out.println(e);
            int len = e.toString().getBytes().length;
            exchange.getResponseHeaders().add("Content-Type", "text/html;charset=utf-8");
            try {
                exchange.sendResponseHeaders(500, len);
                out = exchange.getResponseBody();
                out.write(e.toString().getBytes());
            } catch (Exception e2) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        //拆分参数部分字符串
        String parames = "";
        if (urlString.contains("?")) {
            String[] strs = urlString.split("\\?");
            if (strs.length > 1) {
                parames = strs[1];
            }
        }
        return parames;
    }

    /**
     * 请求参数拆分
     *
     * @param parames
     * @return
     */
    private Map<String, String> getQuerys() {
        Map<String, String> map = new HashMap();
        if (!paramesString.equals("")) {
            String[] strs = null;
            if (!paramesString.contains("&")) {
                strs = new String[]{paramesString};
            } else {
                strs = paramesString.split("&");
            }
            for (String string : strs) {
                if (string.contains("=")) {
                    String[] strs1 = string.split("=");
                    if (strs1.length > 1) {
                        String k = strs1[0];
                        String v = strs1[1];
                        try {
                            v = URLDecoder.decode(v, charset);
                            map.put(k, v);
                        } catch (UnsupportedEncodingException e) {
                            map.put(k, v);
                        }
                    }
                }
            }
        }
        return map;
    }
}

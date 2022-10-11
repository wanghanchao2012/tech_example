package com.example.http;

import com.example.http.HttpServletResponse;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.util.Map;
import java.util.Set;


public class Response implements HttpServletResponse {
    //保存浏览器访问对象
    private HttpExchange exchange;

    /**
     * 保存返回前端编码
     */

    private String charset = "utf-8";

    /**
     * 定义缓冲区大小
     */

    private final int SIZE = 1024 * 1024;

    /**
     * 定义一个缓冲区(采用静态代理)
     */
    private MyByteArrayOutputStream byteBuf = new MyByteArrayOutputStream(SIZE);

    /**
     * 请求头是否已经返回
     */

    private boolean isSendHead = false;

    /**
     * 返回头
     */

    private Headers header;

    /**
     * 重定向地址
     */

    private String reUrl = null;

    /**
     * @param exchange
     */
    public Response(HttpExchange exchange) {
        this.exchange = exchange;
        //初始化headers对象
        header = exchange.getResponseHeaders();
        //默认以text/html返回
        header.add("Content-Type", "text/html");
    }

    @Override
    public void setCharactor(String charset) {
        this.charset = charset;
    }

    @Override
    public OutputStream getOutputStream() {
        return byteBuf;
    }

    @Override
    public PrintStream getWriter() throws UnsupportedEncodingException {
        return new PrintStream(byteBuf, true, charset);
    }

    @Override
    public void setContentTyoe(String type) throws Exception {
        if (isSendHead) {
            throw new Exception("header already send !!!");
        }
        if (header.containsKey("Content-Type")) {
            header.set("Content-Type", type);
            return;
        }
        header.add("Content-Type", type);
    }

    @Override
    public void sendRedirect(String url) {
        reUrl = url;
    }

    public String getReUrl() {
        return reUrl;
    }

    /**
     * 以下方法全部设置为私有方法供反射调用
     */
    private synchronized void flush() {
        try {
            if (!isSendHead) {
                exchange.sendResponseHeaders(200, 0);
                isSendHead = true;
            }
            exchange.getResponseBody().write(byteBuf.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String cookieFormat(Map<String, String> map) {
        Set<String> set = map.keySet();
        StringBuffer buf = new StringBuffer();
        for (String s : set) {
            buf.append(s + "=" + map.get(s) + ";");
        }
        return buf.replace(buf.length() - 1, buf.length(), "").toString();
    }

    /**
     * 直接返回
     */
    public synchronized void response(int code, String data) {
        try {
            if (!isSendHead) {
                Headers head = exchange.getResponseHeaders();
                head.add("Content-Type", "text/html;charset=" + charset);
                exchange.sendResponseHeaders(code, data.getBytes(charset).length);
                isSendHead = true;
            }
            exchange.getResponseBody().write(data.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义一个自己的字节流对象
     *
     * @author Administrator
     */
    class MyByteArrayOutputStream extends ByteArrayOutputStream {
        public MyByteArrayOutputStream(int size) {
            super(size);
        }

        @Override
        public void write(byte[] b) throws IOException {
            if (this.size() + 1 >= SIZE) {
                if (!isSendHead) {
                    exchange.sendResponseHeaders(202, 0);
                    isSendHead = true;
                }
                exchange.getResponseBody().write(byteBuf.toByteArray());
                this.reset();
            }
            super.write(b);
        }

        @Override
        public synchronized void write(byte[] b, int off, int len) {
            if (this.size() + len >= SIZE) {
                try {
                    if (!isSendHead) {
                        exchange.sendResponseHeaders(202, 0);
                        isSendHead = true;
                    }
                    exchange.getResponseBody().write(byteBuf.toByteArray());
                    this.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.write(b, off, len);
        }

        @Override
        public synchronized void write(int b) {
            if (this.size() + 1 >= SIZE) {
                try {
                    if (!isSendHead) {
                        exchange.sendResponseHeaders(202, 0);
                        isSendHead = true;
                    }
                    exchange.getResponseBody().write(byteBuf.toByteArray());
                    this.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.write(b);
        }
    }
}

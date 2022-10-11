package com.example.server;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9999, "com.example.servlet"); //9999表示监听端口，com.servlet表示等下要扫描这个包中的注解
        server.init();
    }
}

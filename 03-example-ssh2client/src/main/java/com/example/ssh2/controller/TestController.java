package com.example.ssh2.controller;

import com.example.ssh2.utils.ScpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/test/")
public class TestController {
    @Autowired
    private ScpHelper scpHelper;

    @GetMapping
    @RequestMapping("/upload")
    public void testUpload() throws IOException {
        ByteArrayInputStream bais = getTextBAIS("你好，scp!6666");
        scpHelper.putFile(bais, "266.txt", "/home/work/apache-tomcat-9.0.16/webapps");
    }

    @GetMapping
    @RequestMapping("/lhUpload")
    public void lhUpload() throws IOException {
        String url = "/Users/wanghanchao/work/zhengda/文档/用户使用手册v2.pdf";
        scpHelper.putFile(url, "/home/work/apache-tomcat-9.0.16/webapps");
    }


    @GetMapping
    @RequestMapping("/download")
    public void download() throws IOException {
        scpHelper.getFile("/home/work/apache-tomcat-9.0.16/webapps/266.txt", "/Users/wanghanchao/Desktop");
    }

    public static ByteArrayInputStream getTextBAIS(String txtStr) {
        try {
            byte[] bytes = txtStr.getBytes("UTF-8");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}

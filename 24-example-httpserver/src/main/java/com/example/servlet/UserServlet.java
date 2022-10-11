package com.example.servlet;

import com.example.annotation.RequestMapper;
import com.example.http.HttpServlet;
import com.example.http.HttpServletRequest;
import com.example.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;

@RequestMapper(path = "/user.do")
public class UserServlet implements HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = request.getParameter("name");
        response.getWriter().print(name);
    }

    @RequestMapper(path = "regist")
    public void regist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("正在注册");
        response.getWriter().print("Success");
    }
}

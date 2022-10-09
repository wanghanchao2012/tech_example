package com.example.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class IOAuth2Filter {

    public abstract boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response)
            throws IOException;

}

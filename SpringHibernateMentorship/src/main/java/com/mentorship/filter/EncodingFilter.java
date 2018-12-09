package com.mentorship.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        EncodeRequest encodeRequest = new EncodeRequest((HttpServletRequest) req);
        filterChain.doFilter(encodeRequest, resp);
    }

    @Override
    public void destroy() {

    }
}

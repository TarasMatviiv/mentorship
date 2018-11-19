package com.mentorship.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        filterChain.doFilter(req, resp);
        resp.setContentType("text/html; charset=UTF-8");
    }

    @Override
    public void destroy() {

    }
}

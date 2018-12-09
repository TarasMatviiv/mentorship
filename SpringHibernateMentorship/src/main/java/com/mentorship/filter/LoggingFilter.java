package com.mentorship.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class LoggingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = req.getParameter(name);
            LOG.info("Name: " + name + "Value: " + value);
        }
        filterChain.doFilter(req, resp);
        LOG.info("Request:" + req.getRemoteAddr() + " " + ((HttpServletRequest) req).getRequestURL());
    }

    @Override
    public void destroy() {

    }
}

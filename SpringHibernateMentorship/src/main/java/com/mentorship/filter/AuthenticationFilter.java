package com.mentorship.filter;

import com.mentorship.servlet.Pages;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Cookie loginCookie = null;
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals("admin")) {
                loginCookie = c;
            }
        }
        if (loginCookie == null) {
            req.setAttribute("errorMessage", "U are not logged!");
//            response.sendRedirect(Pages.INDEX_URI);
            request.getRequestDispatcher(Pages.INDEX).forward(req, resp);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

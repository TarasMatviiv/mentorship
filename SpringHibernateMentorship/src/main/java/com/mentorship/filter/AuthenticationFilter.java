package com.mentorship.filter;

import com.mentorship.servlet.Pages;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Optional<Cookie> loginCookie = Stream.of(request.getCookies())
                .filter(c -> c.getName().equals("admin"))
                .findAny();

        if (loginCookie.isPresent()) {
            filterChain.doFilter(request, response);
        } else {
            req.setAttribute("errorMessage", "U are not logged!");
            request.getRequestDispatcher(Pages.INDEX_FULL).forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}

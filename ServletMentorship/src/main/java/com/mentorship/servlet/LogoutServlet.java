package com.mentorship.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie loginCookie = null;
        for(Cookie c: req.getCookies()) {
            if(c.getName().equals("admin")) {
                loginCookie = c;
            }
        }

        if(loginCookie != null) {
            loginCookie.setMaxAge(0);
            resp.addCookie(loginCookie);
        }
//        req.getRequestDispatcher(Pages.INDEX).forward(req, resp);
        resp.sendRedirect(Pages.INDEX_URI);
    }
}

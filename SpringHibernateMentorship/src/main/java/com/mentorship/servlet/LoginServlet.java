package com.mentorship.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "nimda";
    private static final int ONE_HOUR = 60 * 60;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            Cookie loginCookie = new Cookie("admin", "admin");
            loginCookie.setMaxAge(ONE_HOUR);
            resp.addCookie(loginCookie);
            resp.sendRedirect(Pages.HOME_URI);
//            req.getRequestDispatcher(Pages.HOME).forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Wrong login or password");
            req.getRequestDispatcher(Pages.INDEX).forward(req, resp);
        }
    }
}

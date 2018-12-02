package com.mentorship.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "nimda";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession newSession = req.getSession(true);
            newSession.setMaxInactiveInterval(5*60);
            newSession.setAttribute("messageForAdmin", "Welcome Admin");

            Cookie cookie = new Cookie("testCookie", "testCookie");
            resp.addCookie(cookie);
            req.getRequestDispatcher(Pages.MAIN).forward(req, resp);
//            resp.sendRedirect(Pages.MAIN);
        } else {
            req.setAttribute("errorMessage", "Wrong login or password");
            req.getRequestDispatcher(Pages.INDEX).forward(req, resp);
        }
    }
}

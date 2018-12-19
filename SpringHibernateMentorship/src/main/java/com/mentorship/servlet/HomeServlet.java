package com.mentorship.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller("/home")
public class HomeServlet {

    @RequestMapping
    public String getHomePage() throws ServletException, IOException {
//        req.getRequestDispatcher(Pages.HOME).forward(req, resp);
        return "home";
    }
}

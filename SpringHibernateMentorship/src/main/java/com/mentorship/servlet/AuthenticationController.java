package com.mentorship.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class AuthenticationController {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "nimda";
    private static final int ONE_HOUR = 60 * 60;

    @PostMapping(value = "/logfin")
    public String login(@RequestParam("username") final String username,
                        @RequestParam("password") final String password,
                        final Model model,
                        final HttpServletResponse resp) {

        String page;
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            Cookie loginCookie = new Cookie("admin", "admin");
            loginCookie.setMaxAge(ONE_HOUR);
            resp.addCookie(loginCookie);
            page = Pages.HOME;
        } else {
            model.addAttribute("errorMessage", "Wrong login or password");
            page = Pages.INDEX;
        }
        return page;
    }

    @PostMapping(value = "/logout")
    protected String doPost(final HttpServletRequest req,
                            final HttpServletResponse resp) {

        Optional<Cookie> loginCookieOptional = Stream.of(req.getCookies())
                .filter(c -> c.getName().equals("admin"))
                .findAny();

        if (loginCookieOptional.isPresent()) {
            Cookie cookie = loginCookieOptional.get();
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        return Pages.INDEX_FULL;
    }
}

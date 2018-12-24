package com.mentorship.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        System.out.println("index");
        return Pages.INDEX;
    }

    @GetMapping("/home")
    public String home() {
        System.out.println("HERE");
        return Pages.HOME;
    }
}

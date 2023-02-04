package com.spbproductmanagementjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String showHomePage() {
        return "/homepage/homepage";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/login/login";
    }
}

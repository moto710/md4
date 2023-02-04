package com.cg.spbajaxbankingtransactionjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/customers")
    private String showHomePage() {
        return "/customer/index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/login/login";
    }
}

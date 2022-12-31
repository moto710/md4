package com.validateemail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HomeController {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)$";
    private static Pattern pattern;
    private Matcher matcher;

    public HomeController() {
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    @GetMapping("/")
    private String home() {
        return "/home";
    }

    @PostMapping("/validate")
    private String user(@RequestParam String email, ModelMap modelMap) {
        boolean isValid = this.validate(email);
        if (!isValid) {
            modelMap.addAttribute("message", "Email is invalid");
            return "/home";
        }
        modelMap.addAttribute("email", email);
        return "/success";
    }

    private boolean validate(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
        }

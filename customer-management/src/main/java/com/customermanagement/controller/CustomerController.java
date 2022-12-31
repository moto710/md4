package com.customermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
    @RequestMapping({"/home", "/"})
    private String home() {
        return "/index";
    }
}

package com.cg.spbajaxbankingtransactionjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/", "/customers"})
public class CustomerController {

    @GetMapping("/")
    private ModelAndView index() {

        return new ModelAndView("/customer/index");
    }
}

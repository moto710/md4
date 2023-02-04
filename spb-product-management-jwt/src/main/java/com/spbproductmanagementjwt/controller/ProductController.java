package com.spbproductmanagementjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    private ModelAndView showProduct() {

        return new ModelAndView("product/product");
    }
}

package com.productmanager.controller;

import com.productmanager.model.Product;
import com.productmanager.service.IService;
import com.productmanager.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping({"/", "product"})
public class ProductController {
    private final IService<Product> productIService = new ProductService();
    private List<Product> productList;

    @GetMapping("/")
    private ModelAndView index(Model model) {
        productList = productIService.findAll();
        model.addAttribute("productList", productList);
        return new ModelAndView("/index");
    }
}

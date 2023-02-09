package com.spbproductmanagementjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping({"", "/homepage"})
    private String showHomePage() {
        return "homepage/homepage";
    }

    @GetMapping("/login")
    private String showLoginPage() {
        return "login/login";
    }

    @GetMapping("/signup")
    private String showSignupPage() {
        return "signup/signup";
    }

    @GetMapping("/shop")
    private String showShoppingPage() {
        return "shop/shop";
    }

    @GetMapping("/products")
    private String showProduct() {
        return "product/product";
    }

    @GetMapping("/customers")
    private String showProducts() {
        return "customer/customer";
    }

    @GetMapping("/suspended-products")
    private String showSuspendedProducts() {
        return "product/suspendedProduct";
    }

    @GetMapping("/suspended-customers")
    private String showSuspendedCustomers() {
        return "customer/suspendedCustomer";
    }

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/error/403";
    }
}

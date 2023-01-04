package com.springdatajpa.controller;

import com.springdatajpa.model.Customer;
import com.springdatajpa.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private IService iService;
    @GetMapping("/customer")
    private ModelAndView findAll() {
        List<Customer> customerList = iService.findAll();
        return new ModelAndView("");
    }
}

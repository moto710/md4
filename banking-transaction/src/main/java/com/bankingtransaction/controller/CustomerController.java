package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.service.CustomerService;
import com.bankingtransaction.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping({"/", "/customer"})
public class CustomerController {
    private final IService<Customer> customerService = new CustomerService();
    private List<Customer> customerList;
    @GetMapping("/")
    private ModelAndView index(Model model) {
        customerList = customerService.findAll();
        model.addAttribute("customerList", customerList);
        return new ModelAndView("/index");
    }
}

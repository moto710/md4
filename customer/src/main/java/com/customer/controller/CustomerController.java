package com.customer.controller;

import com.customer.model.Country;
import com.customer.model.Customer;
import com.customer.service.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
    private CustomerDAO customerDAO;
    private List<Customer> customerList;
    @Autowired
    private Country country;
    @GetMapping({"/customer", ""})
    public String showList(ModelMap map) {
        map.addAttribute("country", country);
        return "/list";
    }
}

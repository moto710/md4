package com.testspb.controller;

import com.testspb.model.Customer;
import com.testspb.model.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    private String homePage() {

        Customer customer = new Customer(1L, "Robin", false);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        System.out.println(customerDTO.toString());
        return "homepage/homepage";
    }
}

package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Deposit;
import com.bankingtransaction.service.customer.ICustomerService;
import com.bankingtransaction.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private IDepositService depositService;
    @Autowired
    private ICustomerService customerService;
    private Customer customer;
    private Deposit deposit;
    private ModelAndView modelAndView;
    @GetMapping("/{id}/deposit")
    private ModelAndView showDeposit(@PathVariable int id) {
        customer = customerService.findById(id).get();
        modelAndView = new ModelAndView("/deposit/deposit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
}

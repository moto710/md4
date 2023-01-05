package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Deposit;
import com.bankingtransaction.service.customer.ICustomerService;
import com.bankingtransaction.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;

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
        modelAndView = new ModelAndView("/deposit/deposit");
        if (customerService.findById(id).isPresent()) {
            customer = customerService.findById(id).get();
        }
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping("/{id}/deposit")
    private ModelAndView deposit(@PathVariable int id, @RequestParam double deposit) {
        BigDecimal money = new BigDecimal(deposit);
        depositService.deposits(id, money);
        modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }
}

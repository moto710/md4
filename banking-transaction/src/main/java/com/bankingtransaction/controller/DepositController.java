package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Deposit;
import com.bankingtransaction.service.customer.ICustomerService;
import com.bankingtransaction.service.deposit.IDepositService;
import com.bankingtransaction.utils.InstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Controller
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private IDepositService depositService;
    @Autowired
    private ICustomerService customerService;
    private Customer customer;
    private ModelAndView modelAndView;
    private Optional<Customer> customerOptional;


    @GetMapping("/{id}")
    private ModelAndView showDeposit(@PathVariable int id) {
        modelAndView = new ModelAndView("/account/deposit");
        customerOptional = customerService.findById(id);
        Deposit deposit = new Deposit();
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
        } else {
            customer = customerOptional.get();
            deposit.setCustomer(customer);
            modelAndView.addObject("error", null);
            modelAndView.addObject("deposit", deposit);
        }
        return modelAndView;
    }

    @PostMapping("/{id}")
    private ModelAndView deposit(@PathVariable int id, Deposit deposit) {
        modelAndView = new ModelAndView("/account/deposit");
        customerOptional = customerService.findById(id);
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        if (transactionAmount.compareTo(BigDecimal.ZERO) <= 0) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Deposit amount must be greater than 0");
        } else if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
        } else {
            customer = customerOptional.get();

            BigDecimal currentBalance = customer.getBalance();
            BigDecimal newBalance = currentBalance.add(transactionAmount);
            customer.setBalance(newBalance);
            customer.setUpdatedAt(InstantUtils.instantToString(Instant.now()));

            customerService.deposit(customer, deposit);
            modelAndView.addObject("error", false);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("deposit", deposit);
        }
        return modelAndView;
    }
}

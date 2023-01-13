package com.ajaxbankingtransaction.controller;

import com.ajaxbankingtransaction.model.Customer;
import com.ajaxbankingtransaction.model.Deposit;
import com.ajaxbankingtransaction.service.customer.ICustomerService;
import com.ajaxbankingtransaction.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/deposit")
public class DepositController {
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
            modelAndView.addObject("notFound", true);
            modelAndView.addObject("message", "Customer ID invalid");
        } else {
            customer = customerOptional.get();
            deposit.setCustomer(customer);
            modelAndView.addObject("deposit", deposit);
        }
        return modelAndView;
    }

    @PostMapping("/{id}")
    private ModelAndView deposit(@Validated @ModelAttribute("deposit") Deposit deposit,
                                 BindingResult bindingResult,
                                 @PathVariable int id) {
        modelAndView = new ModelAndView("/account/deposit");
        customerOptional = customerService.findById(id);

        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("redirect:/");
        }
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
                customer.setUpdatedAt(new Date());

                customerService.deposit(customer, deposit);
                modelAndView.addObject("error", false);
                modelAndView.addObject("customer", customer);
                modelAndView.addObject("deposit", deposit);
            }
            return modelAndView;
    }
}

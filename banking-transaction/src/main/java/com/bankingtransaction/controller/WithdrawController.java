package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Withdraw;
import com.bankingtransaction.service.customer.ICustomerService;
import com.bankingtransaction.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController {
    @Autowired
    private ICustomerService customerService;
    private ModelAndView modelAndView;
    private Optional<Customer> customerOptional;
    private Customer customer;

    @GetMapping("/{id}")
    private ModelAndView showWithdraw(@PathVariable int id) {
        modelAndView = new ModelAndView("/account/withdraw");
        Withdraw withdraw = new Withdraw();
        customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("notFound", true);
            modelAndView.addObject("message", "Customer ID invalid");

        } else {
            customer = customerOptional.get();
            withdraw.setCustomer(customer);
            modelAndView.addObject("withdraw", withdraw);
//            modelAndView.addObject("error", null);
        }
        return modelAndView;
    }

    @PostMapping("/{id}")
    private ModelAndView withdraw(@PathVariable int id, Withdraw withdraw) {
        modelAndView = new ModelAndView("/account/withdraw");
        customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");

        } else {
            customer = customerOptional.get();
            BigDecimal currentBalance = customer.getBalance();
            BigDecimal withdrawAmount = withdraw.getTransactionAmount();
            BigDecimal newBalance = currentBalance.subtract(withdrawAmount);

            if (withdrawAmount.compareTo(currentBalance) > 0) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "Your balance is less than withdraw amount!");
            } else if (withdrawAmount.compareTo(BigDecimal.ZERO) <= 0) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "Withdraw amount must be greater than 0");
            } else {
                customer.setUpdatedAt(new Date());
                customer.setBalance(newBalance);
                withdraw.setCustomer(customer);
                customerService.withdraw(customer, withdraw);

                modelAndView.addObject("withdraw", new Withdraw());
                modelAndView.addObject("error", false);
            }
        }
        return modelAndView;
    }
}

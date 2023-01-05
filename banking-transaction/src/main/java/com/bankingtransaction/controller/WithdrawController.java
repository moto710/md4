package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Withdraw;
import com.bankingtransaction.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController {
    @Autowired
    private ICustomerService customerService;
    private ModelAndView modelAndView;
    private Customer customer;
    private Optional<Customer> customerOptional;

    @GetMapping("/{idCustomer}")
    private ModelAndView showWithdraw(@PathVariable int idCustomer) {
        modelAndView = new ModelAndView("/withdraw/withdraw");
        customerOptional = customerService.findById(idCustomer);
        if (customerOptional.isPresent()) {
            modelAndView.addObject("withdraw", new Withdraw());
            modelAndView.addObject("customer", customerOptional.get());
        } else {
            modelAndView.addObject("error", "Customer does not exist!");
        }
        return modelAndView;
    }

    @PostMapping("/{idCustomer}")
    private ModelAndView withdraw(@PathVariable int idCustomer, Withdraw withdraw) {
        modelAndView = new ModelAndView("redirect:/");
        customerOptional = customerService.findById(idCustomer);
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            BigDecimal currentBalance = customer.getBalance();
            BigDecimal withdrawAmount = withdraw.getTransactionAmount();
            BigDecimal newBalance = currentBalance.add(withdrawAmount);

            customer.setBalance(newBalance);
            customerService.withdraw(customer, withdraw);

            modelAndView.addObject("withdraw", new Withdraw());
            modelAndView.addObject("customer", customer);
        } else {
            modelAndView.addObject("error", "Customer does not exist!");
        }
        return modelAndView;
    }
}

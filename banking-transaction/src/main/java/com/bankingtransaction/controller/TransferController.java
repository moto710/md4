package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private ICustomerService customerService;
    private ModelAndView modelAndView;
    private Optional<Customer> customerOptional;
    @GetMapping("/{idSender}")
    private ModelAndView showTransfer(@PathVariable int idSender) {
        modelAndView = new ModelAndView("/account/transfer");
        Customer sender;
        customerOptional = customerService.findById(idSender);
        if (customerOptional.isPresent()) {
            sender = customerOptional.get();
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("customerList", customerService.findAll());
        } else{
            modelAndView.addObject("error", "Invalid sender");
        }
        return modelAndView;
    }
}

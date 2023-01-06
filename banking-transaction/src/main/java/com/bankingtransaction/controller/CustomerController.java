package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.service.customer.ICustomerService;
import com.bankingtransaction.utils.InstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/", "/customer"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    private List<Customer> customerList;
    private ModelAndView modelAndView;
    private Customer customer;
    private Optional<Customer> customerOptional;

    @GetMapping("/edit/{id}")
    private ModelAndView showEdit(@PathVariable int id) {
        modelAndView = new ModelAndView("/customer/edit");
        customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            modelAndView.addObject("error", null);
            modelAndView.addObject("customer", customer);
        } else {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
        }
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    private ModelAndView showDelete(@PathVariable int id) {
        modelAndView = new ModelAndView("/customer/delete");
        customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            modelAndView.addObject("error", false);
            modelAndView.addObject("customer", customer);
        } else {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
        }
        return modelAndView;
    }


    @GetMapping("/create")
    private ModelAndView create() {
        modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @GetMapping("/")
    private ModelAndView index() {
        customerList = (List<Customer>) customerService.findAll();
        modelAndView = new ModelAndView("/customer/index");
        modelAndView.addObject("customerList", customerList);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    private ModelAndView edit(Customer customer) {
        modelAndView = new ModelAndView("redirect:/");
        customer.setUpdatedAt(InstantUtils.instantToString(Instant.now()));
        customerService.save(customer);
        return modelAndView;
    }
    @PostMapping("/{id}/delete")
    private ModelAndView delete(@PathVariable int id) {
        modelAndView = new ModelAndView("redirect:/");
        customerService.remove(id);
        return modelAndView;
    }
    @PostMapping("/create")
    private ModelAndView save(Customer customer) {
        customer.setCreatedAt(InstantUtils.instantToString(Instant.now()));
        customer.setBalance(BigDecimal.ZERO);
        customerService.save(customer);
        return new ModelAndView("redirect:/");
    }
}

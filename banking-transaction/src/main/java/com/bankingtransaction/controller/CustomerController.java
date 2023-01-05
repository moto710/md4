package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.service.customer.ICustomerService;
import com.bankingtransaction.utils.InstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping({"/", "/customer"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    private List<Customer> customerList;
    private ModelAndView modelAndView;
    private Customer customer;

//    @GetMapping("/{id}/deposit")
//    private ModelAndView redirectDeposit(@PathVariable int id) {
//        modelAndView = new ModelAndView("redirect:/deposit");
//        if (customerService.findById(id).isPresent()) {
//            customer = customerService.findById(id).get();
//        }
//        modelAndView.addObject("customer", customerService.findById(id).get());
//        return modelAndView;
//    }
    @GetMapping("/{id}/edit")
    private ModelAndView showEdit(@PathVariable int id) {
        modelAndView = new ModelAndView("/customer/edit");
        if (customerService.findById(id).isPresent()) {
            customer = customerService.findById(id).get();
        }
        modelAndView.addObject("customer", customerService.findById(id).get());
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    private ModelAndView showDelete(@PathVariable int id) {
        modelAndView = new ModelAndView("/customer/delete");
        if (customerService.findById(id).isPresent()) {
            customer = customerService.findById(id).get();
        }
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }


    @GetMapping("/create")
    private ModelAndView create(Model model) {
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
    @PostMapping("/{id}/edit")
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
    @PostMapping("/save")
    private ModelAndView save(Customer customer) {
        customer.setCreatedAt(InstantUtils.instantToString(Instant.now()));
        customer.setBalance(BigDecimal.ZERO);
        customerService.save(customer);
        return new ModelAndView("redirect:/");
    }
}

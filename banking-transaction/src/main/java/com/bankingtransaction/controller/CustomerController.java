package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.service.CustomerService;
import com.bankingtransaction.service.IService;
import com.bankingtransaction.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping({"/", "/customer"})
public class CustomerController {
    private final IService<Customer> customerService = new CustomerService();
    private List<Customer> customerList;

    @GetMapping("/{id}/delete")
    private ModelAndView delete(@PathVariable int id, Model model) {
model.addAttribute("customer", customerService.findById(id));
return new ModelAndView("/delete");
    }
    @PostMapping("/save")
    private ModelAndView save(Customer customer) {
        customer.setId(customerService.findMaxId() + 1);
        customer.setCreatedAt(DateUtils.dateToString(new Date()));
        customerService.add(customer);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/create")
    private ModelAndView create(Model model) {
        model.addAttribute("customer", new Customer());
        return new ModelAndView("/create");
    }
    @GetMapping("/")
    private ModelAndView index(Model model) {
        customerList = customerService.findAll();
        model.addAttribute("customerList", customerList);
        return new ModelAndView("/index");
    }

}

package com.ajaxbankingtransaction.controller;

import com.ajaxbankingtransaction.model.Customer;
import com.ajaxbankingtransaction.model.dto.CustomerEditDTO;
import com.ajaxbankingtransaction.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"", "/customer"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    private List<Customer> customerList;
    private ModelAndView modelAndView;
    private Customer customer;
    private Optional<Customer> customerOptional;

    @GetMapping("/edit/{id}")
    private ModelAndView showEdit(@PathVariable Integer id) {
        modelAndView = new ModelAndView("/customer/edit");
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());

        } else {
            CustomerEditDTO customerEditDTO = new CustomerEditDTO();
            customer = customerOptional.get();

//            customerEditDTO.setId(customer.getId());
            customerEditDTO.setName(customer.getName());
            customerEditDTO.setEmail(customer.getEmail());
            customerEditDTO.setPhone(customer.getPhone());

            if (customer.getDeleted()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "This customer is suspended!");
            }

            modelAndView.addObject("customer", customer);
            modelAndView.addObject("editCustomerDTO", customerEditDTO);
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    private ModelAndView showDelete(@PathVariable Integer id) {
        modelAndView = new ModelAndView("/customer/delete");
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());
        } else {
            customer = customerOptional.get();

            if (customer.getDeleted()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "This customer has been suspended");
            }

            modelAndView.addObject("customer", customer);
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
        customerList = customerService.findAllByDeletedIsFalse();
        modelAndView = new ModelAndView("/customer/index");
        modelAndView.addObject("customerList", customerList);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    private ModelAndView edit(@PathVariable Integer id, @Valid CustomerEditDTO customerEditDTO, BindingResult br) {
        modelAndView = new ModelAndView("/customer/edit");
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());
        } else {
            new CustomerEditDTO().validate(customerEditDTO, br);
            customer = customerOptional.get();

            if (br.hasFieldErrors()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("customer", customer);
                modelAndView.addObject("editCustomerDTO", customerEditDTO);

                return modelAndView;
            }

            customer.setName(customerEditDTO.getName());
            customer.setEmail(customerEditDTO.getEmail());
            customer.setPhone(customerEditDTO.getPhone());
            customer.setUpdatedAt(new Date());

            customerService.save(customer);

            modelAndView.addObject("message", "Edit success!");
            modelAndView.addObject("error", false);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("editCustomerDTO", customerEditDTO);
        }

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    private ModelAndView delete(@PathVariable Integer id) {
        modelAndView = new ModelAndView("/customer/delete");
        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());
        } else {
            customer = customerOptional.get();
            customer.setDeleted(true);
            customerService.save(customer);

            modelAndView.addObject("error", false);
            modelAndView.addObject("customer", customer);
        }

        return modelAndView;
    }

    @PostMapping("/create")
    private ModelAndView save(Customer customer) {
        customer.setCreatedAt(new Date());
        customer.setBalance(BigDecimal.ZERO);
        customerService.save(customer);
        return new ModelAndView("redirect:/");
    }
}

package com.customer.controller;

import com.customer.model.Customer;
import com.customer.service.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
    private CustomerDAO customerDAO;
    private List<Customer> customerList;
    @GetMapping({"/customer", ""})
    public ModelAndView showList(Model model) {
        ModelAndView modelAndView = new ModelAndView("/WEB-INF/views/list.html");
        customerList = customerDAO.selectAll();
        modelAndView.addObject("customerList", customerList);
        return modelAndView;
    }
}

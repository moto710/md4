package com.springdatajpa.service;

import com.springdatajpa.model.Customer;
import com.springdatajpa.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService implements IService<Customer>{
    @Autowired
    private IRepository iRepository;
    @Override
    public List<Customer> findAll() {
        return iRepository.findAll();
    }
}

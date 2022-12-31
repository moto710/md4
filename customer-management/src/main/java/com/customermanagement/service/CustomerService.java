package com.customermanagement.service;

import com.customermanagement.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IService<Customer> {
    private List<Customer> customerList;

    @Override
    public void add(Customer customer) {
        customerList = findAll();
        customerList.add(customer);
    }

    @Override
    public void remove(Customer customer) {
        customerList = findAll();
        customerList.remove(customer);
    }

    @Override
    public void edit(Customer customer) {
    customerList = findAll();
        customerList.removeIf(item -> customer.getId() == item.getId());
    }

    @Override
    public List<Customer> findAll() {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1, "abc", "abc@xyz.com", "VIE"));
        customerList.add(new Customer(2, "xyz", "xyz@abc.vn", "SGP"));
        customerList.add(new Customer(3, "def", "def@com.vn", "CAM"));
        return customerList;
    }
}

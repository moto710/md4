package com.testspb.mapper;

import com.testspb.model.Customer;
import com.testspb.model.CustomerDTO;

public class CustomerMapper {

    private static CustomerMapper instance;

    public static CustomerMapper getInstance() {
        if (instance == null) {
            instance = new CustomerMapper();
        }
        return instance;
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        return new Customer()
                .setId(customerDTO.getId())
                .setName(customerDTO.getName());
    }
}

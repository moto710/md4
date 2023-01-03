package com.springcustomermanagement.service.customer;

import com.springcustomermanagement.model.Customer;
import com.springcustomermanagement.model.Province;
import com.springcustomermanagement.service.IGeneralService;

public interface ICustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
}

package com.bankingtransaction.service.customer;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Withdraw;
import com.bankingtransaction.service.IGeneral;


public interface ICustomerService extends IGeneral<Customer> {

    void withdraw(Customer customer, Withdraw withdraw);

}

package com.ajaxbankingtransaction.service.customer;

import com.ajaxbankingtransaction.model.Customer;
import com.ajaxbankingtransaction.model.Deposit;
import com.ajaxbankingtransaction.model.Transfer;
import com.ajaxbankingtransaction.model.Withdraw;
import com.ajaxbankingtransaction.service.IGeneral;

import java.math.BigDecimal;
import java.util.List;


public interface ICustomerService extends IGeneral<Customer> {
    List<Customer> findAllByDeletedIsFalseAndIdNot(Integer id);
    List<Customer> findAllByDeletedIsFalse();
    List<Customer> findAllByIdNot(int id);
    void deposit(Customer customer, Deposit deposit);
    void withdraw(Customer customer, Withdraw withdraw);
    void transfer(Transfer transfer);

}

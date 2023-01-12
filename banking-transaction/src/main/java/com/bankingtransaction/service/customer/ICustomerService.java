package com.bankingtransaction.service.customer;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Deposit;
import com.bankingtransaction.model.Transfer;
import com.bankingtransaction.model.Withdraw;
import com.bankingtransaction.service.IGeneral;

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

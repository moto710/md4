package com.ajaxbankingtransaction.service.customer;

import com.ajaxbankingtransaction.model.Customer;
import com.ajaxbankingtransaction.model.Deposit;
import com.ajaxbankingtransaction.model.Transfer;
import com.ajaxbankingtransaction.model.Withdraw;
import com.ajaxbankingtransaction.model.dto.CustomerDTO;
import com.ajaxbankingtransaction.service.IGeneral;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneral<Customer> {

    Optional<Customer> findCustomersByIdAndDeletedIsFalse(Integer id);
    Optional<CustomerDTO> findCustomerDTOByIdAndDeletedIsFalse(Integer id);

    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalseAndIdNot(Integer id);
    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse();
    List<Customer> findAllByDeletedIsFalseAndIdNot(Integer id);
    List<Customer> findAllByDeletedIsFalse();
    List<Customer> findAllByIdNot(Integer id);
    void deposit(Deposit deposit);
    void withdraw(Withdraw withdraw);
    void transfer(Transfer transfer);

    void save (Customer customer);

}

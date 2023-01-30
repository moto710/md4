package com.cg.spbajaxbankingtransactionjwt.service.customer;

import com.cg.spbajaxbankingtransactionjwt.model.Customer;
import com.cg.spbajaxbankingtransactionjwt.model.Deposit;
import com.cg.spbajaxbankingtransactionjwt.model.Transfer;
import com.cg.spbajaxbankingtransactionjwt.model.Withdraw;
import com.cg.spbajaxbankingtransactionjwt.model.dto.CustomerDTO;
import com.cg.spbajaxbankingtransactionjwt.service.IGeneral;

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

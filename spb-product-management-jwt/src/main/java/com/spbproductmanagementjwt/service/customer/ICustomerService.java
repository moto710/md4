package com.spbproductmanagementjwt.service.customer;

import com.spbproductmanagementjwt.model.Customer;
import com.spbproductmanagementjwt.model.Deposit;
import com.spbproductmanagementjwt.model.Transfer;
import com.spbproductmanagementjwt.model.Withdraw;
import com.spbproductmanagementjwt.model.dto.CustomerDTO;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLike(String fullName, String email, String phone);

    List<Customer> recipientsList(Long senderId);

    void incrementBalance(Long customerId, BigDecimal transactionAmount);

    void decrementBalance(Long customerId, BigDecimal transactionAmount);

    Optional<Customer> findCustomersByIdAndDeletedIsFalse(Long id);

    Optional<Customer> findCustomersByIdAndDeletedIsTrue(Long id);

    Optional<CustomerDTO> findCustomerDTOByIdAndDeletedIsFalse(Long id);

    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalseAndIdNot(Long id);
    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse();

    List<CustomerDTO> findAllCustomerDTOByDeletedIsTrue();

    List<Customer> findAllByDeletedIsFalseAndIdNot(Long id);
    List<Customer> findAllByDeletedIsFalse();
    List<Customer> findAllByIdNot(Long id);
    void deposit(Deposit deposit);
    void withdraw(Withdraw withdraw);
    void transfer(Transfer transfer);

    void save (Customer customer);

    void deactivate(Long id);

    void reactivate(Long id);
}

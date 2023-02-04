package com.spbproductmanagementjwt.service.customer;

import com.spbproductmanagementjwt.model.Customer;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {

    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLike(String fullName, String email, String phone);

    List<Customer> recipientsList(Long senderId);

    void incrementBalance(Long customerId, BigDecimal transactionAmount);

    void decrementBalance(Long customerId, BigDecimal transactionAmount);

//    Customer deposit(Customer customer, BigDecimal transactionAmount);

//    TransferResponseDTO transfer(TransferDTO transferDTO);
}

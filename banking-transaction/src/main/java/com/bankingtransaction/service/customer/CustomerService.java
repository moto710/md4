package com.bankingtransaction.service.customer;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Withdraw;
import com.bankingtransaction.repository.ICustomerRepository;
import com.bankingtransaction.repository.IWithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
@Service
@Transactional
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IWithdrawRepository withdrawRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void remove(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void withdraw(Customer customer, Withdraw withdraw) {
        int idCustomer = customer.getId();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        withdraw.setCustomer(customer);
        withdrawRepository.save(withdraw);
        customerRepository.increaseBalance(idCustomer, transactionAmount);
    }
}

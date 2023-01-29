package com.ajaxbankingtransaction.service.customer;

import com.ajaxbankingtransaction.model.*;
import com.ajaxbankingtransaction.model.dto.CustomerDTO;
import com.ajaxbankingtransaction.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IWithdrawRepository withdrawRepository;
    @Autowired
    private ITransferRepository transferRepository;
    @Autowired
    private IDepositRepository depositRepository;

    @Autowired
    private ILocationRegionRepository locationRegionRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findCustomersByIdAndDeletedIsFalse(Integer id) {
        return customerRepository.findCustomersByIdAndDeletedIsFalse(id);
    }

    @Override
    public Optional<CustomerDTO> findCustomerDTOByIdAndDeletedIsFalse(Integer id) {
        return customerRepository.findCustomerDTOByIdAndDeletedIsFalse(id);
    }

    @Override
    public List<CustomerDTO> findAllCustomerDTOByDeletedIsFalseAndIdNot(Integer id) {
        return customerRepository.findAllCustomerDTOByDeletedIsFalseAndIdNot(id);
    }

    @Override
    public List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse() {
        return customerRepository.findAllCustomerDTOByDeletedIsFalse();
    }
    @Override
    public List<Customer> findAllByDeletedIsFalseAndIdNot(Integer id) {
        return customerRepository.findAllByDeletedIsFalseAndIdNot(id);
    }

    @Override
    public List<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public List<Customer> findAllByIdNot(Integer id) {
        return customerRepository.findAllByIdNot(id);
    }


    @Override
    public void save(Customer customer) {
        LocationRegion locationRegion = customer.getLocationRegion();
        locationRegionRepository.save(locationRegion);
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
    public void deposit(Deposit deposit) {
        int idCustomer = deposit.getCustomer().getId();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        deposit.setCreatedAt(new Date());
        depositRepository.save(deposit);
        customerRepository.increaseBalance(idCustomer, transactionAmount);
    }

    @Override
    public void withdraw(Withdraw withdraw) {
        int idCustomer = withdraw.getCustomer().getId();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        withdraw.setCreatedAt(new Date());
        withdrawRepository.save(withdraw);
        customerRepository.decreaseBalance(idCustomer, transactionAmount);
    }

    @Override
    public void transfer(Transfer transfer) {
        int idSender = transfer.getSender().getId();
        int idRecipient = transfer.getRecipient().getId();
        BigDecimal fee = new BigDecimal(10);
        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal feeAmount = transferAmount.multiply(fee.divide(BigDecimal.valueOf(100)));
        BigDecimal transactionAmount = feeAmount.add(transferAmount);

        transfer.setCreatedAt(new Date());
        transfer.setFeeAmount(feeAmount);
        transfer.setFee(fee);
        transfer.setTransactionAmount(transactionAmount);
        transfer.setTransferAmount(transferAmount);

        transfer.getSender().setUpdatedAt(new Date());
        transfer.getRecipient().setUpdatedAt(new Date());

        transferRepository.save(transfer);
        customerRepository.decreaseBalance(idSender, transactionAmount);
        customerRepository.increaseBalance(idRecipient, transferAmount);
    }
}

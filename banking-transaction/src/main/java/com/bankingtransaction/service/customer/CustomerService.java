package com.bankingtransaction.service.customer;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Deposit;
import com.bankingtransaction.model.Transfer;
import com.bankingtransaction.model.Withdraw;
import com.bankingtransaction.repository.ICustomerRepository;
import com.bankingtransaction.repository.IDepositRepository;
import com.bankingtransaction.repository.ITransferRepository;
import com.bankingtransaction.repository.IWithdrawRepository;
import com.bankingtransaction.utils.InstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IWithdrawRepository withdrawRepository;
    @Autowired
    private ITransferRepository transferRepository;
    @Autowired
    private IDepositRepository depositRepository;
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
    public List<Customer> findAllByIdNot(int id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public void deposit(Customer customer, Deposit deposit) {
        int idCustomer = customer.getId();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        deposit.setCustomer(customer);
        deposit.setCreateAt(InstantUtils.instantToString(Instant.now()));
        depositRepository.save(deposit);
        customerRepository.increaseBalance(idCustomer, transactionAmount);
    }

    @Override
    public void withdraw(Customer customer, Withdraw withdraw) {
        int idCustomer = customer.getId();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        withdraw.setCustomer(customer);
        withdraw.setCreatedAt(InstantUtils.instantToString(Instant.now()));
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

        transfer.setCreateAt(InstantUtils.instantToString(Instant.now()));
        transfer.setFeeAmount(feeAmount);
        transfer.setFee(fee);
        transfer.setTransactionAmount(transactionAmount);
        transfer.setTransferAmount(transferAmount);

        transfer.getSender().setUpdatedAt(InstantUtils.instantToString(Instant.now()));
        transfer.getRecipient().setUpdatedAt(InstantUtils.instantToString(Instant.now()));

        transferRepository.save(transfer);
        customerRepository.decreaseBalance(idSender, transactionAmount);
        customerRepository.increaseBalance(idRecipient, transferAmount);
    }
}

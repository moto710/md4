package com.spbproductmanagementjwt.service.customer;

import com.spbproductmanagementjwt.model.*;
import com.spbproductmanagementjwt.model.dto.CustomerDTO;
import com.spbproductmanagementjwt.repository.*;
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
    private ILocationRegionRepository locationRegionRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IDepositRepository depositRepository;

    @Autowired
    private ITransferRepository transferRepository;

    @Autowired
    private IWithdrawRepository withdrawRepository;


    @Override
    public void deactivate(Long id) {
        customerRepository.deactivate(id);
    }

    @Override
    public void reactivate(Long id) {
customerRepository.reactivate(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLike(String fullName, String email, String phone) {
        return customerRepository.findAllByFullNameLikeOrEmailLikeOrPhoneLike(fullName, email, phone);
    }

    @Override
    public List<Customer> recipientsList(Long senderId) {
        return customerRepository.findAllByIdNotAndDeletedIsFalse(senderId);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByIdAndDeletedIsFalse(Long id) {
        return customerRepository.findByIdAndDeletedIsFalse(id);
    }

//    @Override
//    public Customer deposit(Customer customer, BigDecimal transactionAmount) {
//        incrementBalance(customer.getId(), transactionAmount);
//
//        Deposit deposit = new Deposit();
//        deposit.setId(null);
//        deposit.setCustomer(customer);
//        deposit.setTransactionAmount(transactionAmount);
//        depositRepository.save(deposit);
//
//        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
//
//        return customerOptional.get();
//    }

//    @Override
//    public TransferResponseDTO transfer(TransferDTO transferDTO) {
//        customerRepository.decrementBalance(transferDTO.getSender().getId(), transferDTO.getTransactionAmount());
//        customerRepository.incrementBalance(transferDTO.getRecipient().getId(), transferDTO.getTransferAmount());
//
//        Transfer transfer = transferDTO.toTransfer();
//        transfer.setId(null);
//        transferRepository.save(transfer);
//
//        Optional<Customer> senderOptional = customerRepository.findById(transferDTO.getSender().getId());
//        Optional<Customer> recipientOptional = customerRepository.findById(transferDTO.getRecipient().getId());
//
//        TransferResponseDTO transferResponseDTO = new TransferResponseDTO();
//        transferResponseDTO.setSender(senderOptional.get().toCustomerDTO());
//        transferResponseDTO.setRecipient(recipientOptional.get().toCustomerDTO());
//
//        return transferResponseDTO;
//    }

    @Override
    public void incrementBalance(Long customerId, BigDecimal transactionAmount) {
        customerRepository.incrementBalance(customerId, transactionAmount);
    }

    @Override
    public void decrementBalance(Long customerId, BigDecimal transactionAmount) {
        customerRepository.decrementBalance(customerId, transactionAmount);
    }

    @Override
    public void save(Customer customer) {

        LocationRegion locationRegion = customer.getLocationRegion();
        locationRegionRepository.save(locationRegion);

        customer.setBalance(BigDecimal.ZERO);
        customer.setLocationRegion(locationRegion);

        customerRepository.save(customer);

    }

    @Override
    public Optional<Customer> findCustomersByIdAndDeletedIsFalse(Long id) {
        return customerRepository.findCustomersByIdAndDeletedIsFalse(id);
    }

    @Override
    public Optional<Customer> findCustomersByIdAndDeletedIsTrue(Long id) {
        return customerRepository.findCustomersByIdAndDeletedIsTrue(id);
    }

    @Override
    public Optional<CustomerDTO> findCustomerDTOByIdAndDeletedIsFalse(Long id) {
        return customerRepository.findCustomerDTOByIdAndDeletedIsFalse(id);
    }

    @Override
    public List<CustomerDTO> findAllCustomerDTOByDeletedIsFalseAndIdNot(Long id) {
        return customerRepository.findAllCustomerDTOByDeletedIsFalseAndIdNot(id);
    }

    @Override
    public List<CustomerDTO> findAllCustomerDTOByDeletedIsTrue(){
        return customerRepository.findAllCustomerDTOByDeletedIsTrue();
    };

    @Override
    public List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse() {
        return customerRepository.findAllCustomerDTOByDeletedIsFalse();
    }

    @Override
    public List<Customer> findAllByDeletedIsFalseAndIdNot(Long id) {
        return customerRepository.findAllByDeletedIsFalseAndIdNot(id);
    }

    @Override
    public List<Customer> findAllByIdNot(Long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public void deposit(Deposit deposit) {
        long idCustomer = deposit.getCustomer().getId();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        deposit.setCreatedAt(new Date());
        depositRepository.save(deposit);
        customerRepository.increaseBalance(idCustomer, transactionAmount);
    }

    @Override
    public void withdraw(Withdraw withdraw) {
        long idCustomer = withdraw.getCustomer().getId();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        withdraw.setCreatedAt(new Date());
        withdrawRepository.save(withdraw);
        customerRepository.decreaseBalance(idCustomer, transactionAmount);
    }

    @Override
    public void transfer(Transfer transfer) {
        long idSender = transfer.getSender().getId();
        long idRecipient = transfer.getRecipient().getId();
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

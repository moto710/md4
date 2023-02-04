package com.spbproductmanagementjwt.service.customer;

import com.spbproductmanagementjwt.model.Customer;
import com.spbproductmanagementjwt.model.LocationRegion;
import com.spbproductmanagementjwt.repository.ICustomerRepository;
import com.spbproductmanagementjwt.repository.ILocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private ILocationRegionRepository locationRegionRepository;

    @Autowired
    private ICustomerRepository customerRepository;

//    @Autowired
//    private DepositRepository depositRepository;

//    @Autowired
//    private TransferRepository transferRepository;


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

        customer.setId(null);
        customer.setBalance(BigDecimal.valueOf(0L));
        customer.setLocationRegion(locationRegion);

        customerRepository.save(customer);

    }
}

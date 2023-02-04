package com.ajaxbankingtransaction.controller.api;

import com.ajaxbankingtransaction.exception.CheckWithdrawBalance;
import com.ajaxbankingtransaction.exception.DataInputException;
import com.ajaxbankingtransaction.model.*;
import com.ajaxbankingtransaction.model.dto.*;
import com.ajaxbankingtransaction.service.customer.ICustomerService;
import com.ajaxbankingtransaction.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AppUtils appUtils;

    private Customer customer;

    private CustomerDTO customerDTO;

    private Optional<CustomerDTO> customerDTOOptional;

    private Optional<Customer> customerOptional;

    private List<CustomerDTO> customerDTOList;

    @GetMapping
    private ResponseEntity<List<CustomerDTO>> getALlCustomers() {

        List<CustomerDTO> customers = customerService.findAllCustomerDTOByDeletedIsFalse();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    private ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer customerId) {

        customerDTOOptional = customerService.findCustomerDTOByIdAndDeletedIsFalse(customerId);

        if (!customerDTOOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customerDTO = customerDTOOptional.get();
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping("/recipients-list/{id}")
    private ResponseEntity<List<CustomerDTO>> getRecipientsList(@PathVariable Integer id) {
        customerDTOList = customerService.findAllCustomerDTOByDeletedIsFalseAndIdNot(id);

        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @PatchMapping("/delete/{id}")
    private ResponseEntity<?> deleteCustomer(@PathVariable Integer id){
        customerDTOOptional = customerService.findCustomerDTOByIdAndDeletedIsFalse(id);

        if (!customerDTOOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customerDTO = customerDTOOptional.get();

        customerDTO.setDeleted(true);

        customer = customerDTO.toCustomer();

        customerService.save(customer);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<?> create(@Validated @RequestBody CustomerCreateDTO customerCreateDTO, BindingResult br) {
        new CustomerCreateDTO().validate(customerCreateDTO, br);

        if (br.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(br);
        }
        customer = customerCreateDTO.toCustomer();

        customer.setBalance(BigDecimal.ZERO);
        customer.setCreatedAt(new Date());
        customerService.save(customer);

        customerDTO = customer.toCustomerDTO();

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Integer id,@Validated @RequestBody CustomerDTO customerUpdateDTO, BindingResult br) {
        new CustomerDTO().validate(customerUpdateDTO, br);

        if (br.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(br);
        }

        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customer = customerOptional.get();
        customer.setName(customerUpdateDTO.getName());
        customer.setEmail(customerUpdateDTO.getEmail());
        customer.setPhone(customerUpdateDTO.getPhone());
        customer.setLocationRegion(customerUpdateDTO.getLocationRegionDTO().toLocationRegion());

        customerService.save(customer);

        customerDTO = customer.toCustomerDTO();

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    private ResponseEntity<?> deposit(@Validated @RequestBody DepositCreateDTO depositCreateDTO, BindingResult br) {
        new DepositCreateDTO().validate(depositCreateDTO, br);

        if (br.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(br);
        }

        Integer id = Integer.parseInt(depositCreateDTO.getCustomerId());
        customerDTOOptional = customerService.findCustomerDTOByIdAndDeletedIsFalse(id);

        if (!customerDTOOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customerDTO = customerDTOOptional.get();

        BigDecimal currentBalance = customerDTO.getBalance();
        BigDecimal transactionAmount = BigDecimal.valueOf(Long.parseLong(depositCreateDTO.getTransactionAmount()));

        if (transactionAmount.compareTo(BigDecimal.TEN) < 0) {
            throw new DataInputException("Minimum amount to deposit is 10$!");
        } else if (transactionAmount.compareTo(BigDecimal.valueOf(1000000000)) > 0) {
            throw new DataInputException("Maximum amount to deposit is 1.000.000.000$!");
        }

        BigDecimal newBalance = currentBalance.add(transactionAmount);

        customer = customerDTO.toCustomer();

        customerDTO.setBalance(newBalance);

        Deposit deposit = new Deposit();
        deposit.setCustomer(customer);
        deposit.setTransactionAmount(transactionAmount);

        customerService.deposit(deposit);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    private ResponseEntity<?> withdraw(@Validated @RequestBody WithdrawCreateDTO withdrawCreateDTO, BindingResult br) {
        new WithdrawCreateDTO().validate(withdrawCreateDTO, br);

        if (br.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(br);
        }

        Integer id = Integer.parseInt(withdrawCreateDTO.getCustomerId());
        customerDTOOptional = customerService.findCustomerDTOByIdAndDeletedIsFalse(id);

        if (!customerDTOOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customerDTO = customerDTOOptional.get();

        BigDecimal currentBalance = customerDTO.getBalance();
        BigDecimal transactionAmount = BigDecimal.valueOf(Long.parseLong(withdrawCreateDTO.getTransactionAmount()));

        if (transactionAmount.compareTo(currentBalance) > 0) {
            throw new CheckWithdrawBalance("Not enough balance to withdraw!");
        } else if (transactionAmount.compareTo(BigDecimal.TEN) < 0) {
            throw new DataInputException("Minimum amount to withdraw is 10$!");
        } else if (transactionAmount.compareTo(BigDecimal.valueOf(1000000000)) > 0) {
            throw new DataInputException("Maximum amount to withdraw is 1.000.000.000$!");
        }

        BigDecimal newBalance = currentBalance.subtract(transactionAmount);

        customer = customerDTO.toCustomer();

        customerDTO.setBalance(newBalance);

        Withdraw withdraw = new Withdraw();
        withdraw.setCustomer(customer);
        withdraw.setTransactionAmount(transactionAmount);

        customerService.withdraw(withdraw);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    private ResponseEntity<?> transfer(@Validated @RequestBody TransferCreateDTO transferCreateDTO, BindingResult br) {
        new TransferCreateDTO().validate(transferCreateDTO, br);

        if (br.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(br);
        }

        Integer senderId = Integer.parseInt(transferCreateDTO.getSenderId());
        Integer recipientId = Integer.parseInt(transferCreateDTO.getRecipientId());
        BigDecimal transferAmount = BigDecimal.valueOf(Long.parseLong(transferCreateDTO.getTransferAmount()));

        if (transferAmount.compareTo(BigDecimal.TEN) < 0) {
            throw new DataInputException("Minimum amount to transfer is 10$!");
        } else if (transferAmount.compareTo(BigDecimal.valueOf(1000000000)) > 0) {
            throw new DataInputException("Maximum amount to transfer is 1.000.000.000$!");
        }

        Optional<CustomerDTO> senderDTOOptional = customerService.findCustomerDTOByIdAndDeletedIsFalse(senderId);
        Optional<CustomerDTO> recipientDTOOptional = customerService.findCustomerDTOByIdAndDeletedIsFalse(recipientId);

        if (!senderDTOOptional.isPresent()) {
            throw new NullPointerException("Sender not found!");
        }
        if (!recipientDTOOptional.isPresent()) {
            throw new NullPointerException("Recipient not found!");
        }

        CustomerDTO senderDTO = senderDTOOptional.get();
        CustomerDTO recipientDTO = recipientDTOOptional.get();

        BigDecimal currentSenderBalance = senderDTO.getBalance();
        BigDecimal currentRecipientBalance = recipientDTO.getBalance();
        BigDecimal fee = BigDecimal.TEN;
        BigDecimal feesAmount = transferAmount.multiply(fee.divide(BigDecimal.valueOf(100)));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (transactionAmount.compareTo(currentSenderBalance) > 0) {
            throw new DataInputException("Not enough balance to do transfer!");
        }

        BigDecimal senderNewBalance = currentSenderBalance.subtract(transactionAmount);
        BigDecimal recipientNewBalance = currentRecipientBalance.add(transferAmount);

        Customer sender = senderDTO.toCustomer();
        Customer recipient = recipientDTO.toCustomer();

        senderDTO.setBalance(senderNewBalance);
        recipientDTO.setBalance(recipientNewBalance);

        Transfer transfer = new Transfer();
        transfer.setFee(BigDecimal.valueOf(10));
        transfer.setFeeAmount(feesAmount);
        transfer.setTransactionAmount(transactionAmount);
        transfer.setTransferAmount(transferAmount);
        transfer.setSender(sender);
        transfer.setRecipient(recipient);

        customerService.transfer(transfer);

        Map<String, CustomerDTO> transferInfo = new HashMap<>();
        transferInfo.put("sender", senderDTO);
        transferInfo.put("recipient", recipientDTO);

        return new ResponseEntity<>(transferInfo, HttpStatus.OK);
    }


}
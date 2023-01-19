package com.ajaxbankingtransaction.controller.api;

import com.ajaxbankingtransaction.exception.DataInputException;
import com.ajaxbankingtransaction.model.Customer;
import com.ajaxbankingtransaction.model.Deposit;
import com.ajaxbankingtransaction.model.Transfer;
import com.ajaxbankingtransaction.model.Withdraw;
import com.ajaxbankingtransaction.model.dto.CustomerCreateDTO;
import com.ajaxbankingtransaction.service.customer.ICustomerService;
import com.ajaxbankingtransaction.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AppUtils appUtils;

    private Customer customer;
    private Optional<Customer> customerOptional;

    private List<Customer> customerList;

    @GetMapping
    public ResponseEntity<List<Customer>> getALlCustomers() {

        List<Customer> customers = customerService.findAllByDeletedIsFalse();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {

        customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customer = customerOptional.get();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/recipients-list/{id}")
    private ResponseEntity<List<Customer>> getRecipientsList(@PathVariable Integer id) {
        customerList = customerService.findAllByDeletedIsFalseAndIdNot(id);

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> create(@Validated @RequestBody CustomerCreateDTO customerCreateDTO, BindingResult bs) {
        new CustomerCreateDTO().validate(customerCreateDTO, bs);

        if (bs.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bs);
        }

        customer = new Customer();
        customer.setName(customerCreateDTO.getName());
        customer.setEmail(customerCreateDTO.getEmail());
        customer.setPhone(customerCreateDTO.getPhone());
        customer.setBalance(BigDecimal.ZERO);

        customerService.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @RequestBody Customer reqCustomer) {
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customer = customerOptional.get();
        customer.setEmail(reqCustomer.getEmail());
        customer.setName(reqCustomer.getName());
        customer.setPhone(reqCustomer.getPhone());
        customer.setDeleted(reqCustomer.getDeleted());

        customerService.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    private ResponseEntity<Customer> deposit(@RequestBody Deposit deposit) {
        Integer id = deposit.getCustomer().getId();
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customer = customerOptional.get();
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        BigDecimal newBalance = currentBalance.add(transactionAmount);

        customer.setBalance(newBalance);
        customerService.deposit(customer, deposit);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    private ResponseEntity<Customer> withdraw(@RequestBody Withdraw withdraw) {
        Integer id = withdraw.getCustomer().getId();
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            throw new NullPointerException("Customer not found!");
        }

        customer = customerOptional.get();
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        BigDecimal newBalance = currentBalance.subtract(transactionAmount);

        customer.setBalance(newBalance);
        customerService.withdraw(customer, withdraw);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    private ResponseEntity<?> transfer(@RequestBody Transfer transfer) {
        Integer senderId = transfer.getSender().getId();
        Integer recipientId = transfer.getRecipient().getId();

        Optional<Customer> senderOptional = customerService.findById(senderId);
        Optional<Customer> recipientOptional = customerService.findById(recipientId);

        if (!senderOptional.isPresent()) {
            throw new NullPointerException("Sender not found!");
        }
        if (!recipientOptional.isPresent()) {
            throw new NullPointerException("Recipient not found!");
        }

        Customer sender = senderOptional.get();
        Customer recipient = recipientOptional.get();

        BigDecimal currentSenderBalance = sender.getBalance();
        BigDecimal currentRecipientBalance = recipient.getBalance();
        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal feesAmount = transferAmount.multiply(BigDecimal.valueOf(10 / 100));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (transactionAmount.compareTo(currentSenderBalance) > 0) {
            throw new DataInputException("Not enough balance to do transfer!");
        }

        BigDecimal senderNewBalance = currentSenderBalance.subtract(transactionAmount);
        BigDecimal recipientNewBalance = currentRecipientBalance.add(transferAmount);

        sender.setBalance(senderNewBalance);
        recipient.setBalance(recipientNewBalance);

        transfer.setSender(sender);
        transfer.setRecipient(recipient);
        transfer.setFee(BigDecimal.valueOf(10));
        transfer.setTransferAmount(transferAmount);
        transfer.setTransactionAmount(transactionAmount);
        transfer.setFeeAmount(feesAmount);

        customerService.transfer(transfer);

        Map<String, Customer> transferInfo = new HashMap<>();
        transferInfo.put("sender", sender);
        transferInfo.put("recipient", recipient);

        return new ResponseEntity<>(transferInfo, HttpStatus.OK);
    }


}
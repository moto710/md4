package com.bankingtransaction.controller;

import com.bankingtransaction.model.Customer;
import com.bankingtransaction.model.Transfer;
import com.bankingtransaction.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private ICustomerService customerService;
    private ModelAndView modelAndView;
    private Optional<Customer> customerOptional;
    @GetMapping("/{idSender}")
    private ModelAndView showTransfer(@PathVariable int idSender) {
        modelAndView = new ModelAndView("/account/transfer");
        Transfer transfer = new Transfer();
        Customer recipient = new Customer();
        Customer sender;
        customerOptional = customerService.findById(idSender);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("error", "Invalid sender");
        } else{
            List<Customer> recipientList = customerService.findAllByIdNot(idSender);
            sender = customerOptional.get();
            transfer.setSender(sender);
            transfer.setRecipient(recipient);

            modelAndView.addObject("recipientList", recipientList);
            modelAndView.addObject("transfer", transfer);
        }
        return modelAndView;
    }

    @PostMapping("/{idSender}")
    private ModelAndView transfer(@PathVariable int idSender, Customer recipient, Transfer transfer) {
        modelAndView = new ModelAndView("/account/transfer");
        Customer sender;
        customerOptional = customerService.findById(idSender);
        if (customerOptional.isPresent()) {
            sender = customerOptional.get();

//            BigDecimal currentSenderBalance = sender.getBalance();
//            BigDecimal fee = transfer.getFee();
//            BigDecimal transferAmount = transfer.getTransferAmount();
//            BigDecimal feeAmount = transferAmount.multiply(fee.divide(BigDecimal.valueOf(100)));
//            BigDecimal transactionAmount = feeAmount.add(transferAmount);
            customerService.transfer(sender, recipient, transfer);
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipient", recipient);
            modelAndView.addObject("transfer", transfer);
        }
        return modelAndView;
    }
}

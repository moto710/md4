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
        Customer sender;
        customerOptional = customerService.findById(idSender);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("notFound", true);
            modelAndView.addObject("message", "Invalid sender");
        } else {
            List<Customer> recipientList = customerService.findAllByDeletedIsFalseAndIdNot(idSender);
            sender = customerOptional.get();
            transfer.setSender(sender);

            modelAndView.addObject("recipientList", recipientList);
            modelAndView.addObject("transfer", transfer);
        }
        return modelAndView;
    }

    @PostMapping("/{idSender}")
    private ModelAndView transfer(@PathVariable int idSender, Transfer transfer) {
        modelAndView = new ModelAndView("/account/transfer");
        Customer sender = null;
        customerOptional = customerService.findById(idSender);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Sender ID invalid");
        } else {
            sender = customerOptional.get();
            int idRecipient = transfer.getRecipient().getId();

            if (idRecipient == idSender) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "Can not transfer for yourself!");
            } else {
                Optional<Customer> recipientOptional = customerService.findById(idRecipient);

                if (!recipientOptional.isPresent()) {
                    modelAndView.addObject("error", true);
                    modelAndView.addObject("message", "Recipient ID invalid");
                } else {
                    BigDecimal currentSenderBalance = sender.getBalance();
                    BigDecimal transferAmount = transfer.getTransferAmount();
                    BigDecimal fee = new BigDecimal(10);
                    BigDecimal feeAmount = transferAmount.multiply(fee.divide(BigDecimal.valueOf(100L)));
                    BigDecimal transactionAmount = feeAmount.add(transferAmount);
                    System.out.println("fee: " + fee);
                    System.out.println("transfer amount: " + transferAmount);
                    System.out.println("fee amount: " + feeAmount);

                    if (transactionAmount.compareTo(BigDecimal.ZERO) <= 0) {
                        modelAndView.addObject("error", true);
                        modelAndView.addObject("message", "Transfer amount must be bigger than 0");
                    } else if (currentSenderBalance.compareTo(transferAmount) < 0) {
                        modelAndView.addObject("error", true);
                        modelAndView.addObject("message", "Sender balance is not enough to transfer");
                    }  else {
                        Customer recipient = recipientOptional.get();

                        transfer.setSender(sender);
                        transfer.setRecipient(recipient);
                        transfer.setTransactionAmount(transactionAmount);
                        customerService.transfer(transfer);

                        modelAndView.addObject("error", false);
                        sender.setBalance(sender.getBalance().subtract(transactionAmount));
                    }
                }
            }
        }
        List<Customer> recipientList = customerService.findAllByIdNot(idSender);
        modelAndView.addObject("sender", sender);
        modelAndView.addObject("recipientList", recipientList);
        modelAndView.addObject("transfer", new Transfer());
        return modelAndView;
    }
}

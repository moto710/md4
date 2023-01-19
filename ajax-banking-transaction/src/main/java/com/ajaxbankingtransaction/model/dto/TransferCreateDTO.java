package com.ajaxbankingtransaction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class TransferCreateDTO implements Validator {

    private String transactionAmount;

    private String senderId;

    private String recipientId;

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TransferCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferCreateDTO transferCreateDTO = (TransferCreateDTO) target;

        String senderId = transferCreateDTO.getSenderId();
        String recipientId = transferCreateDTO.getRecipientId();
        String transactionAmount = transferCreateDTO.getTransactionAmount();

        if (transactionAmount.length() == 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.null", "please fill this transaction amount!");
        } else if (!transactionAmount.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("transactionAmount", "transactionAmount.matches", "Please fill only number!");
        } else if (transactionAmount.compareTo(BigDecimal.valueOf(10).toString()) < 0 || transactionAmount.compareTo(BigDecimal.valueOf(1000000000).toString()) > 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.value", "Only deposit from 10 to 1.000.000.000$");
        }

        if (senderId.length() == 0) {
            errors.rejectValue("senderId", "senderId.null", "Please fill sender's ID!");
        } else if (!senderId.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("senderId", "senderId.matches", "Sender' id is only number!");
        }

        if (recipientId.length() == 0) {
            errors.rejectValue("recipientId", "recipientId.null", "Please fill recipient's ID!");
        } else if (!recipientId.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("recipientId", "recipientId.matches", "Recipient' id is only number!");
        }
    }
}

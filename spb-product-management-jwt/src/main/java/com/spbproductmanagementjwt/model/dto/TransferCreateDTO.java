package com.spbproductmanagementjwt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferCreateDTO implements Validator {

    private String transferAmount;

    private String senderId;

    private String recipientId;

    @Override
    public boolean supports(Class<?> clazz) {
        return TransferCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferCreateDTO transferCreateDTO = (TransferCreateDTO) target;

        String senderId = transferCreateDTO.getSenderId();
        String recipientId = transferCreateDTO.getRecipientId();
        String transferAmount = transferCreateDTO.getTransferAmount();

        if (transferAmount.length() == 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.null", "Transfer amount is required!");
        } else if (!transferAmount.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("transactionAmount", "transactionAmount.matches", "Please fill only number!");
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

        if (senderId.equals(recipientId)) {
            errors.rejectValue("recipientId", "recipientId.same", "You can't transfer to yourself!");
        }
    }

    @Override
    public String toString() {
        return "TransferCreateDTO{" +
                "transferAmount='" + transferAmount + '\'' +
                ", senderId='" + senderId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                '}';
    }
}

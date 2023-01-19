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
public class WithdrawCreateDTO implements Validator {

    private String customerId;

    private String transactionAmount;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return WithdrawCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WithdrawCreateDTO withdrawCreateDTO = (WithdrawCreateDTO) target;

        String customerId = withdrawCreateDTO.getCustomerId();
        String transactionAmount = withdrawCreateDTO.getTransactionAmount();

        if (transactionAmount.length() == 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.null", "please fill this transaction amount!");
        } else if (!transactionAmount.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("transactionAmount", "transactionAmount.matches", "Please fill only number!");
        } else if (transactionAmount.compareTo(BigDecimal.valueOf(10).toString()) < 0 || transactionAmount.compareTo(BigDecimal.valueOf(1000000000).toString()) > 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.value", "Only deposit from 10 to 1.000.000.000$");
        }

        if (customerId.length() == 0) {
            errors.rejectValue("customerId", "customerId.null", "Please fill customer ID!");
        } else if (!customerId.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("customerId", "customerId.matches", "Customer' id is only number!");
        }
    }
}

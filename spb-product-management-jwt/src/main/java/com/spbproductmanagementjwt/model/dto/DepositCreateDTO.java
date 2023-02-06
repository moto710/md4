package com.spbproductmanagementjwt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositCreateDTO implements Validator {

    private String customerId;

    private String transactionAmount;

    @Override
    public boolean supports(Class<?> clazz) {
        return DepositCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DepositCreateDTO depositCreateDTO = (DepositCreateDTO) target;

        String customerId = depositCreateDTO.getCustomerId();
        String transactionAmount = depositCreateDTO.getTransactionAmount();

        if (transactionAmount.length() == 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.null", "please fill this transaction amount!");
        } else if (!transactionAmount.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("transactionAmount", "transactionAmount.matches", "Please fill only number!");
        }

        if (customerId.length() == 0) {
            errors.rejectValue("customerId", "customerId.null", "Please fill customer's ID!");
        } else if (!customerId.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("customerId", "customerId.matches", "Customer' id is only number!");
        }
    }
}

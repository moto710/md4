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
public class CustomerEditDTO implements Validator {

    private String customerId;

    private String name;

    private String email;

    private String phone;

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerEditDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerEditDTO customerEditDTO = (CustomerEditDTO) target;

        String customerId = customerEditDTO.getCustomerId();
        String name = customerEditDTO.getName();
//        String phone = customerEditDTO.getPhone();
        String email = customerEditDTO.getEmail();

        if (name.length() == 0) {
            errors.rejectValue("name", "name.null", "Please fill your name!");
        } else if (name.length() < 4 || name.length() > 50) {
            errors.rejectValue("name", "name.length", "Name is from 4 to 50 letters!");
        }

//        if (phone.length() > 11 || phone.length() < 10) {
//            errors.rejectValue("number", "number.length", "Please fill your phone number!");
//        } else if (!phone.startsWith("0") || !phone.startsWith("9")) {
//            errors.rejectValue("number", "number.startsWith", "Phone number must start with 0");
//        } else if (!phone.matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$")) {
//            errors.rejectValue("number", "number.matches", "Wrong phone number format, sample: 0912345678");
//        }

        if (email.length() == 0) {
            errors.rejectValue("email", "email.null", "Please fill your email address!");
        } else if (!email.matches("^[\\w]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
            errors.rejectValue("email", "email.matches", "Wrong email format, sample: abc@domain.com");
        }

        if (customerId.length() == 0) {
            errors.rejectValue("customerId", "customerId.null", "Please fill customer ID!");
        } else if (!customerId.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("customerId", "customerId.matches", "Customer' id is only number!");
        }
    }
}

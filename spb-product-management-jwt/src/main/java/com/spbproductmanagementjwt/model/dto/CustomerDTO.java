package com.spbproductmanagementjwt.model.dto;

import com.spbproductmanagementjwt.model.Customer;
import com.spbproductmanagementjwt.model.LocationRegion;
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
public class CustomerDTO implements Validator {

    private Long id;
    private String fullName;
    private String email;
    private String phone;

    private Boolean deleted;
    private LocationRegionDTO locationRegionDTO;
    private BigDecimal balance;

    public CustomerDTO (Long id, String fullName, String email, String phone, BigDecimal balance, Boolean deleted, LocationRegion locationRegion){
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
        this.email = email;
        this.phone = phone;
        this.deleted = deleted;
        this.locationRegionDTO = locationRegion.toLocationRegionDTO();
    }

    public Customer toCustomer() {
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setLocationRegion(locationRegionDTO.toLocationRegion())
                .setBalance(balance);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) target;
        String name = customerDTO.getFullName();
        String email = customerDTO.getEmail();
//        String phone = customerDTO.getPhone();

        LocationRegionDTO locationRegionDTO = customerDTO.getLocationRegionDTO();
        String address = locationRegionDTO.getAddress();

        if (name.length() == 0) {
            errors.rejectValue("name", "name.null", "Please fill your name!");
        } else if (name.length() < 4 || name.length() > 50) {
            errors.rejectValue("name", "name.length", "Name is from 4 to 50 letters!");
        }

//        if (phone.length() > 11 || phone.length() < 10) {
//            errors.rejectValue("phone", "phone.length", "Please fill your phone number!");
//        } else if (!phone.matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$")) {
//            errors.rejectValue("phone", "phone.matches", "Wrong phone number format, sample: 0912345678");
//        }

        if (email.length() == 0) {
            errors.rejectValue("email", "email.null", "Please fill your email address!");
        } else if (!email.matches("^[\\w]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
            errors.rejectValue("email", "email.matches", "Wrong email format, sample: abc@domain.com");
        }

        if (address.length() == 0) {
            errors.rejectValue("address", "address.null", "Please fill your address!");
        } else if (address.length() < 4 || address.length() > 50) {
            errors.rejectValue("address", "address.length", "Address is from 4 to 50 letters!");
        }
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", deleted=" + deleted +
                ", balance=" + balance +
                '}';
    }
}

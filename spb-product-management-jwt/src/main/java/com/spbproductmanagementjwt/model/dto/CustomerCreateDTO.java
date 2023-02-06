package com.spbproductmanagementjwt.model.dto;

import com.spbproductmanagementjwt.model.Customer;
import lombok.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO implements Validator {

    private String fullName;

    private String email;

    private String phone;

    private LocationRegionDTO locationRegionDTO;

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerCreateDTO customerCreateDTO = (CustomerCreateDTO) target;
        String name = customerCreateDTO.getFullName();
        String email = customerCreateDTO.getEmail();
//        String phone = customerCreateDTO.getPhone();
//        String provinceId = customerCreateDTO.getProvinceId();
//        String provinceName = customerCreateDTO.getProvinceName();
//        String districtId = customerCreateDTO.getDistrictId();
//        String wardId = customerCreateDTO.getWardId();
        String address = customerCreateDTO.getLocationRegionDTO().getAddress();

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

//        if (provinceId.length() == 0) {
//            errors.rejectValue("provinceId", "provinceId.null", "Can't leave blank province's ID!");
//        } else if (!provinceId.matches("(^$|[0-9]*$)")) {
//            errors.rejectValue("provinceId", "provinceId.matches", "Province's ID is only number!");
//        }
//
//        if (provinceName.length() == 0) {
//            errors.rejectValue("provinceName", "provinceName.null", "Can't leave blank province's name!");
//        }
//
//        if (districtId.length() == 0) {
//            errors.rejectValue("districtId", "districtId.null", "Can't leave blank district's ID!");
//        } else if (!districtId.matches("(^$|[0-9]*$)")) {
//            errors.rejectValue("districtId", "districtId.matches", "District's ID is only number!");
//        }
//
//        if (districtName.length() == 0) {
//            errors.rejectValue("districtName", "districtName.null", "Can't leave blank district's name!");
//        }
//
//        if (wardId.length() == 0) {
//            errors.rejectValue("wardId", "wardId.null", "Can't leave blank ward's ID!");
//        } else if (!wardId.matches("(^$|[0-9]*$)")) {
//            errors.rejectValue("wardId", "wardId.matches", "Ward's ID is only number!");
//        }
//
//        if (wardName.length() == 0) {
//            errors.rejectValue("wardName", "wardName.null", "Can't leave blank ward's name!");
//        }
//
        if (address.length() == 0) {
            errors.rejectValue("address", "address.null", "Can't leave blank your address!");
        } else if (address.length() < 5) {
            errors.rejectValue("address", "address.length", "Address must be more than 5 letters!");
        }
    }

    public Customer toCustomer() {
        return new Customer()
                .setFullName(fullName)
                .setPhone(phone)
                .setEmail(email)
                .setLocationRegion(locationRegionDTO.toLocationRegion());
    }
}

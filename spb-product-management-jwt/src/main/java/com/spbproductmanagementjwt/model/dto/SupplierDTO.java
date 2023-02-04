package com.spbproductmanagementjwt.model.dto;

import com.spbproductmanagementjwt.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO implements Validator {

    private String name;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SupplierDTO supplierDTO = (SupplierDTO) target;
        String name = supplierDTO.getName();

        if (name.length() == 0) {
            errors.rejectValue("name", "name.null", "Please fill supplier's name!");
        } else if (name.length() < 4 || name.length() > 50) {
            errors.rejectValue("name", "name.length", "Name is from 4 to 50 letters!");
        }
    }

    public Supplier toSupplier() {
        return new Supplier()
                .setName(name);
    }
}

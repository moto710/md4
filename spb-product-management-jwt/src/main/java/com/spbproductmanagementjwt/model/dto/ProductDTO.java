package com.spbproductmanagementjwt.model.dto;

import com.spbproductmanagementjwt.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDTO implements Validator {

    private Long id;

    private String name;

    private String price;
    private String quantity;

    private SupplierDTO supplierDTO;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        String name = productDTO.getName();
        String price = productDTO.getPrice();
        String quantity = productDTO.getQuantity();
        String supplierName = productDTO.getSupplierDTO().getName();

        if (name.length() == 0) {
            errors.rejectValue("name", "name.null", "Please fill your name!");
        } else if (name.length() < 4 || name.length() > 50) {
            errors.rejectValue("name", "name.length", "Name is from 4 to 50 letters!");
        }

        if (quantity.length() == 0) {
            errors.rejectValue("quantity", "quantity.null", "Please fill product's quantity!");
        }

        if (price.length() == 0) {
            errors.rejectValue("price", "price.null", "Please fill product's price!");
        } else if (price.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("price", "price.matches", "Please fill only number!");
        }

        if (supplierName.length() == 0) {
            errors.rejectValue("supplierName", "supplierName.null", "Please fill product's supplier name!");
        } else if (supplierName.length() < 4 || supplierName.length() > 50) {
            errors.rejectValue("supplierName", "supplierName.length", "Name is from 4 to 50 letters!");
        }
    }

    public Product toProduct() {
        return new Product()
                .setId(id)
                .setName(name)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setQuantity(Double.parseDouble(quantity))
                .setSupplier(supplierDTO.toSupplier());
    }
}

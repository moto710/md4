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
public class ProductCreateDTO implements Validator {

    private Long id;
    private String name;
    private String price;
    private String description;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCreateDTO productCreateDTO = (ProductCreateDTO) target;
        String name = productCreateDTO.getName();
        String price = productCreateDTO.getPrice();

        if (name.length() == 0) {
            errors.rejectValue("name", "name.null", "Please fill product's name!");
        } else if (name.length() < 4 || name.length() > 50) {
            errors.rejectValue("name", "name.length", "Product's name is from 4 to 50 letters!");
        }

        if (price.length() == 0) {
            errors.rejectValue("price", "price.null", "Please fill product's price!");
        } else if (!price.matches("^$|[0-9]*$")) {
            errors.rejectValue("price", "price.matches", "Product's price is only number!");
        }
    }

    public Product toProduct() {
        return new Product()
                .setId(id)
                .setName(name)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setDescription(description);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

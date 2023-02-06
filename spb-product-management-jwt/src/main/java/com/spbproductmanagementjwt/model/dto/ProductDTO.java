package com.spbproductmanagementjwt.model.dto;

import com.spbproductmanagementjwt.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDTO {

    private Long id;

    private String name;

    private String price;

    private String description;

    public Product toProduct() {
        return new Product()
                .setName(name)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
//                .setSupplier(supplierDTO.toSupplier())
                ;
    }
}

package com.spbproductmanagementjwt.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductResponseDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private String fileName;

    private String folderName;
}

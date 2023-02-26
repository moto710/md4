package com.spbproductmanagementjwt.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartRequestDTO {

    private Long productId;

    private Long quantity;
}

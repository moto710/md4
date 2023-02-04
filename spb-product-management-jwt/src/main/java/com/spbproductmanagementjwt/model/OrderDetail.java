package com.spbproductmanagementjwt.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_title", nullable = false)
    private String productTitle;

    @Column(name = "product_price", precision = 10, scale = 0, nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_quantity", nullable = false)
    private Long productQuantity;

    @Column(name = "product_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal productAmount;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;
}

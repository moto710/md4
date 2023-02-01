package com.spbproductmanagementjwt.model;

import com.spbproductmanagementjwt.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "quantities")
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id",referencedColumnName = "id", nullable = false)
    private Supplier supplier;

    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(id)
                .setName(name)
                .setQuantity(quantity.toString())
                .setSupplierDTO(supplier.toSupplierDTO());
    }
}

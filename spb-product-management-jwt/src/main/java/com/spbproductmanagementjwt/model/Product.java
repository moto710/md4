package com.spbproductmanagementjwt.model;

import com.spbproductmanagementjwt.model.dto.ProductCreateDTO;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    public ProductCreateDTO toProductDTO() {
        return new ProductCreateDTO()
                .setName(name)
                .setPrice(price.toString())
                .setDescription(description)
                ;
    }

    public ProductResponseDTO toProductResponseDTO(ProductMedia productMedia) {
        return new ProductResponseDTO()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setDescription(description)
                .setFolderName(productMedia.getFileFolder())
                .setFileName(productMedia.getFileName())
                ;
    }

    public ProductResponseDTO toProductResponseDTO() {
        return new ProductResponseDTO()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setDescription(description)
                ;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}

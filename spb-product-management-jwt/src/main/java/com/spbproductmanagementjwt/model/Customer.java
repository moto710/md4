package com.spbproductmanagementjwt.model;

import com.spbproductmanagementjwt.model.dto.CustomerDTO;
import com.spbproductmanagementjwt.model.dto.CustomerResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "customers")
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

    @Column(precision = 12, scale = 0, nullable = false, updatable = false)
    private BigDecimal balance;


    public CustomerResponseDTO toCustomerResponseDTO() {
        return new CustomerResponseDTO()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                .setBalance(balance);
    }

    public CustomerDTO toCustomerDTO() {
        return new CustomerDTO()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                .setBalance(balance);
    }
}

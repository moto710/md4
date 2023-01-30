package com.cg.spbajaxbankingtransactionjwt.model;

import com.cg.spbajaxbankingtransactionjwt.model.dto.CustomerDTO;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@Component
@Accessors(chain = true)
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String name;

    @Column(precision = 12, nullable = false, updatable = false)
    private BigDecimal balance;

    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    private String phone;

    @OneToOne
    @JoinColumn(name = "location_region_id",referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

//    @OneToMany(targetEntity = Deposit.class, fetch = FetchType.EAGER)
//    private List<Deposit> deposits;
//
//    @OneToMany(targetEntity = Transfer.class)
//    private List<Transfer> transfers;

    public Customer (Integer id, String name, BigDecimal balance, String email, String phone, Boolean deleted, LocationRegion locationRegion){
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.email = email;
        this.phone = phone;
        this.deleted = deleted;
        this.locationRegion = locationRegion;
    }
    public CustomerDTO toCustomerDTO() {
        return new CustomerDTO()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(balance)
                .setDeleted(deleted)
                .setLocationRegionDTO(locationRegion.toLocationRegionDTO());
    }

}

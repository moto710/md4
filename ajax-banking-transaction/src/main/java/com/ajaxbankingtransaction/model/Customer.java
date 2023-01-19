package com.ajaxbankingtransaction.model;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
@Component
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String name;

    @NotBlank
    @Column(precision = 12, nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, unique = true)
    private String email;

//    @OneToOne
//    @JoinColumn(name = "location_region_id",referencedColumnName = "id")
//    private LocationRegion locationRegion;

    @NotBlank
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public LocationRegion getLocationRegion() {
//        return locationRegion;
//    }

//    public void setLocationRegion(LocationRegion locationRegion) {
//        this.locationRegion = locationRegion;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

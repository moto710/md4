package com.spbproductmanagementjwt.model;

import com.spbproductmanagementjwt.model.enums.ERole;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

//    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
//    private List<User> users;
}

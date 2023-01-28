package com.phonenumbermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "smartphones")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String producer;

    private String model;

    private double price;
}

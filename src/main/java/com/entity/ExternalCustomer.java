package com.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ExternalCustomer")
@Data
public class ExternalCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "basic_wage", nullable = false)
    private double basicWage;

    @Column(name = "allowance", nullable = false)
    private double allowance;

    // Constructors, getters, and setters
}

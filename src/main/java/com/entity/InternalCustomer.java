package com.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "InternalCustomer")
@Data
public class InternalCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "total_wage", nullable = false)
    private double totalWage;

    // Constructors, getters, and setters
}

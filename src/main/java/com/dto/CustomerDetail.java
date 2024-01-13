package com.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDetail {
    private int id;
    private String firstname;
    private String lastname;
    private double totalWage;
}

package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFileDTO {
    private String recordType;
    private String firstname;
    private String lastname;
    private String totalWage;
}

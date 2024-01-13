package com.config;

import com.dto.CustomerDetail;
import com.dto.CustomerFileDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FixedLengthProcessor implements ItemProcessor<CustomerDetail, CustomerFileDTO> {

    String AMOUNT_FORMAT = "%014.2f";
    String NAME_FORMAT = "%50s";

    @Override
    public CustomerFileDTO process(CustomerDetail item) throws Exception {
        return CustomerFileDTO.builder()
                .recordType("1")
                .firstname(getFirstName(item.getFirstname()))
                .lastname(getLastName(item.getLastname()))
                .totalWage(getAmount(item.getTotalWage()))
                .build();
    }

    private String getAmount(double totalWage){
        return String.format(AMOUNT_FORMAT,totalWage);
    }

    private String getFirstName(String firstName){
        return String.format(NAME_FORMAT,firstName);
    }

    private String getLastName(String lastName){
        return String.format(NAME_FORMAT,lastName);
    }
}

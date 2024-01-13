package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.dto.CustomerDetail;
import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<CustomerDetail> {


    @Override
    public CustomerDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CustomerDetail.builder()
                .id(rs.getInt("Id"))
                .firstname(rs.getString("FirstName"))
                .lastname(rs.getString("LastName"))
                .totalWage(rs.getDouble("TotalWage"))
                .build();
    }
}

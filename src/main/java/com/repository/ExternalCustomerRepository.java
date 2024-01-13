package com.repository;

import com.entity.ExternalCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalCustomerRepository extends JpaRepository<ExternalCustomer, Long> {

    // You can add custom query methods if needed

}

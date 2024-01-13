package com.repository;

import com.entity.InternalCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalCustomerRepository extends JpaRepository<InternalCustomer, Long> {

    // You can add custom query methods if needed

}

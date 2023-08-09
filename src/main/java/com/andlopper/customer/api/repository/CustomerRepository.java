package com.andlopper.customer.api.repository;

import com.andlopper.customer.api.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
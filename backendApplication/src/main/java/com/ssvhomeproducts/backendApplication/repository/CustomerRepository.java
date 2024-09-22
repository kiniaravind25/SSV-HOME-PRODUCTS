package com.ssvhomeproducts.backendApplication.repository;

import com.ssvhomeproducts.backendApplication.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


    @Repository
    public interface CustomerRepository extends JpaRepository<Customer, Long> {
        Optional<Customer> findByName(String name);
    }

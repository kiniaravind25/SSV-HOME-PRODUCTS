package com.ssvhomeproducts.backendApplication.repository;

import com.ssvhomeproducts.backendApplication.entity.Customer;
import com.ssvhomeproducts.backendApplication.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerAndOrderDateBetween(Customer customer, LocalDate startDate, LocalDate endDate);
}


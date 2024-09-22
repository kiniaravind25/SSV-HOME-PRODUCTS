package com.ssvhomeproducts.backendApplication.service;

import com.ssvhomeproducts.backendApplication.entity.Customer;
import com.ssvhomeproducts.backendApplication.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomerByName(String name) {
        return customerRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void updateCustomerBalance(Customer customer, Double amountPaid) {
        customer.setOldBalance(customer.getOldBalance() - amountPaid);
        customerRepository.save(customer);
    }
}

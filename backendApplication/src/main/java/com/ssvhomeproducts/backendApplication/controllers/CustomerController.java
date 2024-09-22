package com.ssvhomeproducts.backendApplication.controllers;

import com.ssvhomeproducts.backendApplication.entity.Customer;
import com.ssvhomeproducts.backendApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        Customer customer = customerService.findCustomerByName(name);
        return ResponseEntity.ok(customer);
    }
}

package com.ssvhomeproducts.backendApplication.service;

import com.ssvhomeproducts.backendApplication.entity.Customer;
import com.ssvhomeproducts.backendApplication.entity.OrderItem;
import com.ssvhomeproducts.backendApplication.entity.Orders;
import com.ssvhomeproducts.backendApplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    public List<Orders> getOrdersByDateRange(String customerName, int days) {
        Customer customer = customerService.findCustomerByName(customerName);
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days);
        return orderRepository.findByCustomerAndOrderDateBetween(customer, startDate, endDate);
    }

    public Orders placeOrder(String customerName, List<OrderItem> items, Double amountPaid) {
        Customer customer = customerService.findCustomerByName(customerName);

        // Calculate total price
        double totalPrice = items.stream()
                .mapToDouble(item -> item.getItemPrice() * item.getQuantity())
                .sum();


        Orders orders = new Orders();
        orders.setOrderDate(LocalDate.now());
        orders.setCustomer(customer);
        orders.setTotalPrice(totalPrice);
        orders.setOrderItems(items);
        items.forEach(item -> item.setOrder(orders));


        if (amountPaid > 0) {
            customerService.updateCustomerBalance(customer, amountPaid);
        }


       return orderRepository.save(orders);
    }
}

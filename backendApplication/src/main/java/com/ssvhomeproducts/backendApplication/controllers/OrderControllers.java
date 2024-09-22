package com.ssvhomeproducts.backendApplication.controllers;


import com.ssvhomeproducts.backendApplication.dto.OrderItemRequest;
import com.ssvhomeproducts.backendApplication.entity.Item;
import com.ssvhomeproducts.backendApplication.entity.OrderItem;
import com.ssvhomeproducts.backendApplication.entity.Orders;
import com.ssvhomeproducts.backendApplication.service.ItemService;
import com.ssvhomeproducts.backendApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/order")
public class OrderControllers {

    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestParam String customerName,
                                              @RequestBody List<OrderItemRequest> itemsRequest,
                                              @RequestParam Double amountPaid) {

        List<OrderItem> items = new ArrayList<>();

        // Fetch each item from the database and create OrderItem
        for (OrderItemRequest itemRequest : itemsRequest) {
            Item item = itemService.getItemById(itemRequest.getItemId());
            OrderItem orderItem = new OrderItem();
            orderItem.setItemName(item.getName());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setItemPrice(item.getPrice());
            items.add(orderItem);
        }

        Orders orders = orderService.placeOrder(customerName, items, amountPaid);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/history/{customerName}/{days}")
    public ResponseEntity<List<Orders>> getOrderHistory(@PathVariable String customerName, @PathVariable int days) {
        List<Orders> orders = orderService.getOrdersByDateRange(customerName, days);
        return ResponseEntity.ok(orders);
    }
}

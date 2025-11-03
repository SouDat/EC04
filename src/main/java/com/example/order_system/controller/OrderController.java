package com.example.order_system.controller;

import com.example.order_system.model.Customer;
import com.example.order_system.model.Orders;
import com.example.order_system.repository.CustomerRepository;
import com.example.order_system.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/addOrder")
    public Orders addOrders(@PathVariable Long customerId, @RequestBody Orders orders){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            orders.setCustomer(customer.get());
            return orderRepository.save(orders);
        }
        throw new RuntimeException("Customer not found");
    }

    @GetMapping
    public Iterable<Orders> getOrdersList(){
        return orderRepository.findAll();
    }

}

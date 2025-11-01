package com.example.order_system.controller;

import com.example.order_system.dto.Customer;
import com.example.order_system.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

//    @PostMapping("/remove")
//    public Customer removeCustomer(@RequestBody Customer customer){
//        return customerRepository.delete(customer);
//    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}

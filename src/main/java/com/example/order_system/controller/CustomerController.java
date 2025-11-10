package com.example.order_system.controller;

import com.example.order_system.dto.ApiResponse;
import com.example.order_system.exception.ResourceNotFoundException;
import com.example.order_system.model.Customer;
import com.example.order_system.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCustomer(@Valid @RequestBody Customer customer){
        Customer saved = customerRepository.save(customer);
        log.info("Created new customer: {}", saved.getName());
        return ResponseEntity.ok(new ApiResponse("Customer created successfully", saved));
    }

//    @DeleteMapping("/remove/{id}")
//    public ResponseEntity<ApiResponse> removeCustomer(@Valid @PathVariable Long id) {
//        if (customerRepository.existsById(id)) {
//            Customer delete = customerRepository.deleteById(id);
//            return ResponseEntity.ok(new ApiResponse("Customer deleted successfully", delete));
//        } else {
//            return ResponseEntity.ok(new ApiResponse("Customer not deleted",));
//        }
//    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return ResponseEntity.ok(new ApiResponse("Customer found", customer));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(new ApiResponse("All the customers are: ", customers));
    }
}

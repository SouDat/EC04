package com.example.order_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    @Min(value = 1, message = "Amount must be at least 1")
    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

package com.example.order_system.dto;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String number;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getNumber() {
        return number;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Orders> getOrders() {
        return orders;
    }




}

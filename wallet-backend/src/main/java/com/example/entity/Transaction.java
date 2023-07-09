package com.example.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="userTable")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private double amount;

    public Transaction() {
    }

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

}

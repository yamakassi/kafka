package com.example.kafkaproducer.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductRequest {
    private UUID productId;
    private String productName;
    private String description;
    private Long price;
    private String brand;
}

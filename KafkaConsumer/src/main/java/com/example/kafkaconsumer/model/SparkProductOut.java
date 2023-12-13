package com.example.kafkaconsumer.model;

import lombok.Data;

@Data
public class SparkProductOut {
    private String brand;
    private int totalPrice;
    private int count;
}

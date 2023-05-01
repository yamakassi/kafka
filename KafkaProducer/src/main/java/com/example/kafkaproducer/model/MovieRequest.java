package com.example.kafkaproducer.model;

import lombok.Data;

@Data
public class MovieRequest {
    String name;
    String description;
    int year;
}

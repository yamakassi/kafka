package com.example.kafkaconsumer.model;

import lombok.Data;

@Data
public class MovieRequest {
    String name;
    String description;
    int year;
}

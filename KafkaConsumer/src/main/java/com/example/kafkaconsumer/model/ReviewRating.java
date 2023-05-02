package com.example.kafkaconsumer.model;

import lombok.Data;

@Data
public class ReviewRating {
    private int id;
    private String name;
    private int rating;
}
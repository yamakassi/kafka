package com.example.kafkaconsumer.model;

import lombok.Data;

@Data
public class ReviewRequest {
    private String name;
    private int rating;
    private String text;
    private int movieId;
}
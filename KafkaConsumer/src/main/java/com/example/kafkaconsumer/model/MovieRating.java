package com.example.kafkaconsumer.model;

import lombok.Data;

@Data
public class MovieRating {
    private long id;
    private String name;
    private float rating;
}

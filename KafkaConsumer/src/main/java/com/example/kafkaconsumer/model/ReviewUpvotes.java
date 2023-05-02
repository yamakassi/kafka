package com.example.kafkaconsumer.model;

import lombok.Data;

@Data
public class ReviewUpvotes {
    private int id;
    private String name;
    private int upvotes;
}

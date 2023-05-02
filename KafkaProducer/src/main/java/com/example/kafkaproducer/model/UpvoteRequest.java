package com.example.kafkaproducer.model;

import lombok.Data;

@Data
public class UpvoteRequest {
    private int reviewId;
    private int upvotesNum;
}

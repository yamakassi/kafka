package com.example.kafkaconsumer.services;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.entities.ReviewEntity;
import com.example.kafkaconsumer.model.ReviewRating;
import com.example.kafkaconsumer.model.ReviewUpvotes;

import java.util.List;

public interface ReviewService {
    List<ReviewEntity> getReviews();
    List<ReviewEntity> getMovieReviews(MovieEntity movieEntity);
    List<ReviewUpvotes> getTopUpvotedMovieReviews(MovieEntity movieEntity);

    List<ReviewRating> getTopRatedMovieReviews(MovieEntity movieEntity);
}

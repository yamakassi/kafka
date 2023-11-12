package com.example.kafkaconsumer.services;

import java.util.List;

public interface ReviewService {
    List<ReviewEntity> getReviews();
    List<ReviewEntity> getMovieReviews(MovieEntity movieEntity);
    List<ReviewUpvotes> getTopUpvotedMovieReviews(MovieEntity movieEntity);

    List<ReviewRating> getTopRatedMovieReviews(MovieEntity movieEntity);
}

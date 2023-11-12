package com.example.kafkaconsumer.services;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getMovies();
    float getMovieRating(int movieId);

    List<ReviewUpvotes> getTopReviewsOfMovie(int movieId);

    List<ReviewRating> getTopRatedReviewsOfMovie(int movieId);

    List<MovieRating> getTopMovies();
}

package com.example.kafkaconsumer.services;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.model.MovieRating;
import com.example.kafkaconsumer.model.ReviewRating;
import com.example.kafkaconsumer.model.ReviewUpvotes;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getMovies();
    float getMovieRating(int movieId);

    List<ReviewUpvotes> getTopReviewsOfMovie(int movieId);

    List<ReviewRating> getTopRatedReviewsOfMovie(int movieId);

    List<MovieRating> getTopMovies();
}

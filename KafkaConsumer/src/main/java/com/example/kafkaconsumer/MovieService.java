package com.example.kafkaconsumer;

import com.example.kafkaconsumer.entities.MovieEntity;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getMovies();
}

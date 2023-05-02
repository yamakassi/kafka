package com.example.kafkaconsumer.repositories;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.entities.ReviewEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAll();
    List<ReviewEntity> findAllByMovie(MovieEntity movie);
}
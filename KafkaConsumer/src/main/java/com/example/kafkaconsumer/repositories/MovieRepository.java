package com.example.kafkaconsumer.repositories;

import com.example.kafkaconsumer.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    List<MovieEntity> findAll();
}

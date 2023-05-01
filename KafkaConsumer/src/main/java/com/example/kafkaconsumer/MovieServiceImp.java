package com.example.kafkaconsumer;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.repositories.MovieRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImp implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }
}

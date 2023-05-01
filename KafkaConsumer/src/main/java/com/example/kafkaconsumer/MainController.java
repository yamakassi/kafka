package com.example.kafkaconsumer;

import com.example.kafkaconsumer.entities.MovieEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MainController {

    private final MovieService movieService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<MovieEntity> getMovies() {
        return movieService.getMovies();
    }
}

package com.example.kafkaconsumer;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.entities.ReviewEntity;
import com.example.kafkaconsumer.model.MovieRating;
import com.example.kafkaconsumer.model.ReviewRating;
import com.example.kafkaconsumer.model.ReviewUpvotes;
import com.example.kafkaconsumer.services.MovieService;
import com.example.kafkaconsumer.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MainController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<MovieEntity> getMovies() {
        return movieService.getMovies();
    }

    @RequestMapping(value = "/movies/top", method = RequestMethod.GET)
    public List<MovieRating> getTopMovies() {
        return movieService.getTopMovies();
    }

    @RequestMapping(value = "/rating/{id}", method = RequestMethod.GET)
    public float getMovieRating(@PathVariable("id") final int id) {
        return movieService.getMovieRating(id);
    }

    @RequestMapping(value = "/topReviews/{id}", method = RequestMethod.GET)
    public List<ReviewUpvotes> getReviewsTop(@PathVariable("id") final int id) {
        return movieService.getTopReviewsOfMovie(id);
    }

    @RequestMapping(value = "/mostRatedReviews/{id}", method = RequestMethod.GET)
    public List<ReviewRating> getMostRatedReviews(@PathVariable("id") final int id) {
        return movieService.getTopRatedReviewsOfMovie(id);
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public List<ReviewEntity> getReviews() {
        return reviewService.getReviews();
    }
}

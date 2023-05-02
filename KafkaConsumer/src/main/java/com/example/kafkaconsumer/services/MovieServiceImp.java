package com.example.kafkaconsumer.services;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.entities.ReviewEntity;
import com.example.kafkaconsumer.model.MovieRating;
import com.example.kafkaconsumer.model.ReviewRating;
import com.example.kafkaconsumer.model.ReviewUpvotes;
import com.example.kafkaconsumer.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImp implements MovieService {
    private final MovieRepository movieRepository;
    private final ReviewService reviewService;

    @Override
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public float getMovieRating(int movieId) {
        MovieEntity me = movieRepository.findById((long) movieId).get();
        if(me == null) throw new IllegalArgumentException("Invalid ID");

        List<ReviewEntity> rs = reviewService.getMovieReviews(me);
        System.out.println(rs);
        float raiting = 0;
        int c = 0;
        for (ReviewEntity re : rs) {
            c++;
            raiting += re.getRating();
        }
        if(c > 0)
            raiting /= c;
        return raiting;
    }

    @Override
    public List<ReviewUpvotes> getTopReviewsOfMovie(int movieId) {
        MovieEntity me = movieRepository.findById((long) movieId).get();
        if(me == null) throw new IllegalArgumentException("Invalid ID");

        return reviewService.getTopUpvotedMovieReviews(me);
    }

    @Override
    public List<ReviewRating> getTopRatedReviewsOfMovie(int movieId) {
        MovieEntity me = movieRepository.findById((long) movieId).get();
        if(me == null) throw new IllegalArgumentException("Invalid ID");

        return reviewService.getTopRatedMovieReviews(me);
    }

    @Override
    public List<MovieRating> getTopMovies() {
        List<MovieEntity> mes = getMovies();
        List<MovieRating> mrs = new ArrayList<>();

        for (MovieEntity me : mes) {
            MovieRating mr = new MovieRating();
            mr.setId(me.getId());
            mr.setName(me.getName());
            mr.setRating(getMovieRating(me.getId().intValue()));
            mrs.add(mr);
        }

        boolean f = true;
        while (f) {
            f = false;
            for (int i = 0; i < mrs.size() - 1; i++) {
                if(mrs.get(i).getRating() < mrs.get(i + 1).getRating()) {
                    f = true;
                    MovieRating mr = mrs.get(i + 1);
                    mrs.set(i + 1, mrs.get(i));
                    mrs.set(i, mr);
                }
            }
        }

        return mrs;
    }
}

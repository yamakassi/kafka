package com.example.kafkaconsumer.services;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.entities.ReviewEntity;
import com.example.kafkaconsumer.model.ReviewRating;
import com.example.kafkaconsumer.model.ReviewUpvotes;
import com.example.kafkaconsumer.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService{
    private final ReviewRepository reviewRepository;
    @Override
    public List<ReviewEntity> getReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<ReviewEntity> getMovieReviews(MovieEntity movieEntity) {
        return reviewRepository.findAllByMovie(movieEntity);
    }

    @Override
    public List<ReviewUpvotes> getTopUpvotedMovieReviews(MovieEntity movieEntity) {
        List<ReviewEntity> res = getMovieReviews(movieEntity);

        List<ReviewUpvotes> mus = new ArrayList<>();

        for (ReviewEntity re : res) {
            ReviewUpvotes ru = new ReviewUpvotes();
            ru.setId(re.getId().intValue());
            ru.setName(re.getName());
            ru.setUpvotes(re.getUpvotes());
            mus.add(ru);
        }

        boolean f = true;
        while (f) {
            f = false;
            for (int i = 0; i < mus.size() - 1; i++) {
                if(mus.get(i).getUpvotes() < mus.get(i + 1).getUpvotes()) {
                    f = true;
                    ReviewUpvotes ru = mus.get(i + 1);
                    mus.set(i + 1, mus.get(i));
                    mus.set(i, ru);
                }
            }
        }
        return mus;
    }

    @Override
    public List<ReviewRating> getTopRatedMovieReviews(MovieEntity movieEntity) {
        List<ReviewEntity> res = getMovieReviews(movieEntity);

        List<ReviewRating> mus = new ArrayList<>();

        for (ReviewEntity re : res) {
            ReviewRating ru = new ReviewRating();
            ru.setId(re.getId().intValue());
            ru.setName(re.getName());
            ru.setRating(re.getRating());
            mus.add(ru);
        }

        boolean f = true;
        while (f) {
            f = false;
            for (int i = 0; i < mus.size() - 1; i++) {
                if(mus.get(i).getRating() < mus.get(i + 1).getRating()) {
                    f = true;
                    ReviewRating ru = mus.get(i + 1);
                    mus.set(i + 1, mus.get(i));
                    mus.set(i, ru);
                }
            }
        }
        return mus;
    }
}
package com.example.kafkaproducer.resource;

import com.example.kafkaproducer.ApiClient;
import com.example.kafkaproducer.model.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieResource {

    @Autowired
    private KafkaTemplate<String, MovieRequest> movieKafkaTemplate;

    private final ApiClient apiClient;

    @Value("${MOVIE_TOPIC}")
    private String MOVIE_TOPIC;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public MovieRequest addMovie(@RequestBody MovieRequest request) {
        movieKafkaTemplate.send(MOVIE_TOPIC, request);
        return request;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String movies() {
        return apiClient.getResponse("/movies");
    }

    @RequestMapping(value = "/rating/{id}", method = RequestMethod.GET)
    public String movieRating(@PathVariable("id") final int id) {
        return apiClient.getResponse("/rating/" + id);
    }

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public String movieTop() {
        return apiClient.getResponse("/movies/top");
    }

    @RequestMapping(value = "/upvoteSortedReviews/{id}", method = RequestMethod.GET)
    public String movieUpvoteReviews(@PathVariable("id") final int id) {
        return apiClient.getResponse("/topReviews/" + id);
    }

    @RequestMapping(value = "/positiveSortedReviews/{id}", method = RequestMethod.GET)
    public String moviePositiveReviews(@PathVariable("id") final int id) {
        return apiClient.getResponse("/mostRatedReviews/" + id);
    }
}
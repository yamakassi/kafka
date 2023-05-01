package com.example.kafkaconsumer.listener;

import com.example.kafkaconsumer.entities.MovieEntity;
import com.example.kafkaconsumer.model.MovieRequest;
import com.example.kafkaconsumer.model.User;
import com.example.kafkaconsumer.repositories.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MovieRepository movieRepository;

    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MovieRequest movie = objectMapper.readValue(message, MovieRequest.class);
        System.out.println("Consumed message: " + message);
        System.out.println(movie);
        MovieEntity m = new MovieEntity();
        m.setName(movie.getName());
        m.setDescription(movie.getDescription());
        m.setYear(movie.getYear());
        movieRepository.save(m);
        System.out.println(movieRepository.findAll());
    }


    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
            containerFactory = "movieKafkaListenerFactory")
    public void consumeJson(MovieRequest movie) {
        System.out.println("Consumed JSON Message: " + movie);
        MovieEntity m = new MovieEntity();
        m.setName(movie.getName());
        m.setDescription(movie.getDescription());
        m.setYear(movie.getYear());
        movieRepository.save(m);
        System.out.println(movieRepository.findAll());
    }
}
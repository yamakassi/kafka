package com.example.kafkaproducer.config;

import com.example.kafkaproducer.model.MovieRequest;
import com.example.kafkaproducer.model.ReviewRequest;
import com.example.kafkaproducer.model.UpvoteRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    @Value("${BOOTSTRAP_SERVERS_CONFIG}")
    private String BOOTSTRAP_SERVERS_CONFIG;

    @Value("${MOVIE_TOPIC}")
    private String MOVIE_TOPIC;

    @Value("${REVIEW_TOPIC}")
    private String REVIEW_TOPIC;

    @Value("${UPVOTE_TOPIC}")
    private String UPVOTE_TOPIC;

    @Bean
    public NewTopic topicMovie() {
        return TopicBuilder.name(MOVIE_TOPIC)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicReview() {
        return TopicBuilder.name(REVIEW_TOPIC)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicUpvote() {
        return TopicBuilder.name(UPVOTE_TOPIC)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public ProducerFactory<String, MovieRequest> producerMovieFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, MovieRequest> kafkaMovieTemplate() {
        return new KafkaTemplate<>(producerMovieFactory());
    }

    @Bean
    public ProducerFactory<String, ReviewRequest> producerReviewFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, ReviewRequest> kafkaReviewTemplate() {
        return new KafkaTemplate<>(producerReviewFactory());
    }

    @Bean
    public ProducerFactory<String, UpvoteRequest> producerUpvoteFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, UpvoteRequest> kafkaUpvoteTemplate() {
        return new KafkaTemplate<>(producerUpvoteFactory());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
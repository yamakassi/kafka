package com.example.kafkaproducer.config;

import com.example.kafkaproducer.model.MovieRequest;
import com.example.kafkaproducer.model.ReviewRequest;
import com.example.kafkaproducer.model.UpvoteRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic topicMovie() {
        return TopicBuilder.name("MOVIE")
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicReview() {
        return TopicBuilder.name("REVIEW")
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicUpvote() {
        return TopicBuilder.name("UPVOTE")
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public ProducerFactory<String, MovieRequest> producerMovieFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
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

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
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

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, UpvoteRequest> kafkaUpvoteTemplate() {
        return new KafkaTemplate<>(producerUpvoteFactory());
    }
}
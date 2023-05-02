package com.example.kafkaconsumer.config;

import com.example.kafkaconsumer.model.MovieRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
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
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, MovieRequest> movieConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(MovieRequest.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MovieRequest> movieKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MovieRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(movieConsumerFactory());
        return factory;
    }
}
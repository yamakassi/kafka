package com.example.kafkaconsumer.listener;

import com.example.kafkaconsumer.entities.ProductEntity;
import com.example.kafkaconsumer.model.ProductRequest;
import com.example.kafkaconsumer.model.SparkProductOut;
import com.example.kafkaconsumer.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ProductRepository productRepository;
    private final  RedisTemplate<String, Integer> redisTemplate;

    @KafkaListener(topics = {"${SPARK_TOPIC}"}, groupId = "group_id")
    public void consumeProduct(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequest product = objectMapper.readValue(message, ProductRequest.class);
        System.out.println("Consumed message: " + message);
        System.out.println(product);
        ProductEntity m = new ProductEntity();
        m.setProductId(product.getProductId());
        m.setProductName(product.getProductName());
        m.setDescription(product.getDescription());
        m.setPrice(product.getPrice());
        m.setBrand(product.getBrand());
        productRepository.save(m);

    }

    @KafkaListener(topics = {"SPARK_OUT"}, groupId = "group_id")
    public void consumeSparkOut(String message) throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
        SparkProductOut product = objectMapper.readValue(message, SparkProductOut.class);
        updateBrandDataInRedis(product);
    }

    private void updateBrandDataInRedis(SparkProductOut product) {
        System.out.println("redis: " + product);
        redisTemplate.opsForValue().set(product.getBrand().trim(), product.getTotalPrice());
    }
}
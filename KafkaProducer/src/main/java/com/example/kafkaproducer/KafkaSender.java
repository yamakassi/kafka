package com.example.kafkaproducer;

import com.example.kafkaproducer.generator.GeneratorProduct;
import com.example.kafkaproducer.model.ProductRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
    @Value("${SPARK_TOPIC}")
    private String SPARK_TOPIC;
    private final KafkaTemplate<String, ProductRequest> productKafkaTemplate;

    private final GeneratorProduct generatorProduct;


    public KafkaSender(KafkaTemplate<String, ProductRequest> productKafkaTemplate, GeneratorProduct generatorProduct) {
        this.productKafkaTemplate = productKafkaTemplate;
        this.generatorProduct = generatorProduct;
    }


    @Scheduled(fixedRate = 5000)
    public void sender() {
        ProductRequest product = generatorProduct.generateProduct();
        System.out.println(product);
        productKafkaTemplate.send(SPARK_TOPIC, product);
    }

}

package com.example.kafkaproducer.resource;

import com.example.kafkaproducer.model.MovieRequest;
import com.example.kafkaproducer.model.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewResource {

    @Autowired
    private KafkaTemplate<String, ReviewRequest> reviewKafkaTemplate;

    @Value("${REVIEW_TOPIC}")
    private String REVIEW_TOPIC;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReviewRequest addMovie(@RequestBody ReviewRequest request) {
        reviewKafkaTemplate.send(REVIEW_TOPIC, request);
        return request;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String movies() {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://consumer:8081/reviews";

        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl, String.class);

        String productsJson = response.getBody();

        System.out.println(productsJson);
        return productsJson;
    }
}
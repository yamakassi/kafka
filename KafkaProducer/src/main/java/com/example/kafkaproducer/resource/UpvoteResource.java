package com.example.kafkaproducer.resource;

import com.example.kafkaproducer.model.ReviewRequest;
import com.example.kafkaproducer.model.UpvoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upvote")
public class UpvoteResource {
    @Autowired
    private KafkaTemplate<String, UpvoteRequest> upvoteKafkaTemplate;

    @Value("${UPVOTE_TOPIC}")
    private String UPVOTE_TOPIC;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UpvoteRequest addMovie(@RequestBody UpvoteRequest request) {
        upvoteKafkaTemplate.send(UPVOTE_TOPIC, request);
        return request;
    }
}

package com.example.kafkaproducer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiClient {
    @Value("${CONSUMER_ADR}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;

    public String getResponse(String path) {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://" + url + path, String.class);

        String productsJson = response.getBody();

        System.out.println(productsJson);
        return productsJson;
    }
}

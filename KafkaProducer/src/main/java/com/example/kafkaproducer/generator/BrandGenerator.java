package com.example.kafkaproducer.generator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class BrandGenerator {

    private final List<String> predefinedBrands;

    public BrandGenerator() {
        this.predefinedBrands = List.of("Acme", "Initech", "Globex", "Soylent", "Virtucon", "Umbrella", "Wayne Enterprises", "Stark Industries", "Strickland Propane", "Weyland-Yutani");
    }

    public String generateBrand() {
        int randomIndex = new Random().nextInt(predefinedBrands.size());
        return predefinedBrands.get(randomIndex);
    }
}
package com.example.kafkaproducer.generator;

import com.example.kafkaproducer.model.ProductRequest;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class GeneratorProduct {

    private final Faker faker;
    private final BrandGenerator brandGenerator;

    public GeneratorProduct(BrandGenerator brandGenerator) {
        this.faker = new Faker();
        this.brandGenerator = brandGenerator;
    }

    public ProductRequest generateProduct() {
        ProductRequest product = new ProductRequest();
        product.setProductId(UUID.randomUUID());
        product.setProductName(faker.commerce().productName());
        product.setDescription(faker.lorem().sentence());
        product.setPrice(faker.number().randomNumber(3, false));
        product.setBrand(brandGenerator.generateBrand());

        return product;
    }
}


package com.example.kafkaconsumer.repositories;

import com.example.kafkaconsumer.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
    List<ProductEntity> findAll();
}
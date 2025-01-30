package com.github.sfidencio.domain.repository;

import com.github.sfidencio.domain.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface IProductRepository extends ReactiveMongoRepository<Product, UUID> {
}

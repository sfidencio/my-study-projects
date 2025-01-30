package com.github.sfidencio.aspectlog.application.controller;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    private List<Product> products;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam(value = "name", required = false) String name) {
        var result = this.products
                .stream()
                .filter(product -> product.getName()
                        .contains(name))
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostConstruct
    public void init() {
        var product = Product.builder()
                .id(UUID.randomUUID())
                .name("Arroz")
                .description("Description of product 1")
                .price(100.0)
                .build();
        var product2 = Product.builder()
                .id(UUID.randomUUID())
                .name("Macarrao")
                .description("Description of product 2")
                .price(200.0)
                .build();
        var product3 = Product.builder()
                .id(UUID.randomUUID())
                .name("Feijao Preto")
                .description("Description of product 3")
                .price(300.0)
                .build();

        var product4 = Product.builder()
                .id(UUID.randomUUID())
                .name("Feijao Branco")
                .description("Description of product 4")
                .price(400.0)
                .build();
        var product5 = Product.builder()
                .id(UUID.randomUUID())
                .name("Amendoim Roxo")
                .description("Description of product 5")
                .price(500.0)
                .build();
        var product6 = Product.builder()
                .id(UUID.randomUUID())
                .name("Banana")
                .description("Description of product 6")
                .price(600.0)
                .build();
        this.products = List.of(product, product2, product3, product4, product5,product6);
    }

    @Data
    @Builder
    public static class Product {
        private UUID id;
        private String name;
        private String description;
        private double price;
    }
}

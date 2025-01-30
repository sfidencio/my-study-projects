package com.github.sfidencio.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private UUID id = UUID.randomUUID();
    private String description;
    private BigDecimal price;
}

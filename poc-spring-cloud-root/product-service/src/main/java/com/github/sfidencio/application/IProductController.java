package com.github.sfidencio.application;

import com.github.sfidencio.application.dto.ProductRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IProductController {
    ResponseEntity<?> createProduct(ProductRequestDTO productDTO);
}

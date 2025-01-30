package com.gihub.sfidencio.mybusiness.application;

import com.gihub.sfidencio.mybusiness.domain.model.Product;
import com.gihub.sfidencio.mybusiness.domain.service.ProductService;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class Application {
    public static void main(String[] args) {
        var logger = Logger.getLogger(Application.class.getName());
        try {
            var product = new Product(1L, "Product 1", BigDecimal.ZERO, BigDecimal.ONE);
            var productService = new ProductService();
            productService.save(product);
        } catch (Throwable e) {
            logger.info(e.getMessage());
        }
    }
}

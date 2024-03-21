package com.github.sfidencio.exemplo.domain.validators.imp;

import com.github.sfidencio.exemplo.domain.entities.Product;
import com.github.sfidencio.exemplo.domain.validators.ValidProduct;

public class CheckIfStockGreater100AndPriceLessThan525 implements ValidProduct {
    @Override
    public void execute(Product product) {
        if (product.getStock().intValue() >= 100 && product.getPrice().doubleValue() <= 5.25)
            throw new IllegalArgumentException("Stock cannot be greater than 100 and price less than 5.25");
    }
}

package domain.validator;

import dto.Product;

public interface CalculateHandler {
    double suggest(Product product);
}

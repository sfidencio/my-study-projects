package domain.validator;

import dto.Product;

public class AnyPrice implements CalculateHandler {
    @Override
    public double suggest(Product product) {
        return product.getPrice() * 0.20;
    }
}

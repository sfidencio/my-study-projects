package domain.validator;

import dto.Product;

public class BetweenTenAndTwenty implements CalculateHandler {
    @Override
    public double suggest(Product product) {
        if (product.getPrice() >= 10 && product.getPrice() <= 20)
            return product.getPrice() * 0.10;
        return new BetweenTwentyAndFifty().suggest(product);
    }
}

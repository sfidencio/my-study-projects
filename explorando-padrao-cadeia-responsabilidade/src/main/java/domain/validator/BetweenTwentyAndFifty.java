package domain.validator;

import dto.Product;

public class BetweenTwentyAndFifty implements CalculateHandler {
    @Override
    public double suggest(Product product) {
        if (product.getPrice() >= 20 && product.getPrice() <= 50)
            return product.getPrice() * 0.10;
        return new AnyPrice().suggest(product);
    }
}

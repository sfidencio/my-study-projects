package domain;

import domain.validator.CalculateHandler;
import dto.Product;

public class SugestionService {
    private final CalculateHandler calculateHandler;

    public SugestionService(CalculateHandler calculateHandler) {
        this.calculateHandler = calculateHandler;
    }


    public Double calculateSugestion(Product product) {
        return calculateHandler.suggest(product);
    }
}

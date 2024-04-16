import domain.SugestionService;
import domain.validator.BetweenTenAndTwenty;
import dto.Product;

public class Application {
    public static void main(String[] args) {
        SugestionService sugestionService = new SugestionService(new BetweenTenAndTwenty());
        //se price entre 10 - 20 -> sugestão 10%
        //se price entre 20 - 50 -> sugestão 15%
        //se price entre 50 - 100 -> sugestão 20%
        Product product = new Product("1234567890123", "Product Name", 45);
        double result = sugestionService.calculateSugestion(product);
        System.out.println("Sugestion: " + result);
    }

}

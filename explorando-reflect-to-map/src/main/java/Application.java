import java.lang.reflect.InvocationTargetException;

public class Application {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Product p = new Product(1,"feijao",2.20,0,20);
        System.out.println(p.toMap());
    }
}

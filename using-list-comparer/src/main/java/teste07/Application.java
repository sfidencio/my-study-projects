package teste07;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Application {
    public static void main(String[] args) {
        List<Object> objetosDesconhecidos = List.of(10, 10.0, "10", 10L, 10.0f, true, new
                ArrayList<>(), new Vector<>());

        objetosDesconhecidos.stream().forEach(
                System.out::println
        );

        objetosDesconhecidos.stream().forEach(
                objeto -> System.out.println(objeto.getClass().getName()));

    }
}

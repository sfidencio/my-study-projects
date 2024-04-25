import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicationElements {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(2);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.add(8);
        lista.add(8);


        checkDuplicityBasic(lista);
        System.out.println("------------");
        checkDuplicityStream(lista);



    }

    private static void checkDuplicityBasic(List<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).equals(lista.get(j))) {
                    System.out.println("Duplicado: " + lista.get(i));
                }
            }
        }
    }

    private static void checkDuplicityStream(List<Integer> lista) {
        lista.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> System.out.println("Duplicado: " + e.getKey()));

    }
}

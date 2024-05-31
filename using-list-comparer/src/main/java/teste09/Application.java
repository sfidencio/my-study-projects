package teste09;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Integer> numbers1to50 = List.of(45,50);
//
//        numbers1to50.stream()
//                .filter(number -> number % 2 == 0)
//                .forEach(System.out::println);
//
//        numbers1to50.stream()
//                .anyMatch(
//                        number -> number % 2 == 0
//                );


//        numbers1to50.stream()
//                .filter(n->n>=45 && n<=50)
//                .forEach(System.out::println);

        var result = numbers1to50.stream()
                .allMatch(
                        n->n>=45 && n<=50
                );

        System.out.println(result);
    }
}

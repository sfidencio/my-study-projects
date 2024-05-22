package teste04;

import java.util.List;

/*
 * Exemplos de uso do método anyMatch e AllMatch do Stream
 */

public class Application {
    public static void main(String[] args) {

        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numbers2 = List.of(1, 3, 5, 7, 9);
        //Se algum número for par, imprime true
        System.out.println(numbers1.stream().anyMatch(
                number -> number % 2 == 0
        ));

        System.out.println(numbers2.stream().anyMatch(
                number -> number % 2 == 0
        ));
        System.out.println();

        //Se todos os números forem ímpares, imprime true
        System.out.println(numbers1.stream().allMatch(
                number -> number % 2 != 0
        ));

        System.out.println(numbers2.stream().allMatch(
                number -> number % 2 != 0
        ));
    }
}

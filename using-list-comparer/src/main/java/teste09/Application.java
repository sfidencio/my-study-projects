package teste09;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Integer> numbers1to50 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50);

        numbers1to50.stream()
                .filter(number -> number % 2 == 0)
                .forEach(System.out::println);

        numbers1to50.stream()
                .anyMatch(
                        number -> number % 2 == 0
                );

    }
}

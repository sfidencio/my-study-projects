package com.github.sfidencio.lambdas;

import java.util.List;
import java.util.function.Predicate;

public class L11 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> predicate = i -> i % 2 == 0;
        list.stream().filter(predicate).forEach(System.out::println);
        System.out.println("************************************");

        //And
        Predicate<Integer> predicate2 = i -> i > 6;
        list.stream().filter(predicate.and(predicate2)).forEach(System.out::println);

    }
}

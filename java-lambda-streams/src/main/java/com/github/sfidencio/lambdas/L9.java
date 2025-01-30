package com.github.sfidencio.lambdas;

import java.util.List;
import java.util.function.Predicate;

public class L9 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> predicate = i -> i % 2 == 0;
        list.stream().filter(predicate).forEach(System.out::println);
    }
}

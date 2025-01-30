package com.github.sfidencio.lambdas;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class L8 {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() < 5;
        var result = predicate.test("Hello World!");
        System.out.println(result);
    }
}

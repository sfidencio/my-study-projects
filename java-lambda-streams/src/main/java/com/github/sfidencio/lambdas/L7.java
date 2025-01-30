package com.github.sfidencio.lambdas;

import java.util.function.BiFunction;
import java.util.function.Function;

public class L7 {
    public static void main(String[] args) {
        BiFunction<String, String, String> juntaAcomB = (a, b) -> a.concat(" - ").concat(b);
        var result = juntaAcomB.apply("Hello", "World!");
        System.out.println(result);
    }
}

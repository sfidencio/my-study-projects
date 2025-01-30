package com.github.sfidencio.lambdas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class L6 {
    public static void main(String[] args) {
        Function<String,Integer> sizeString = (s) -> s.length();
        var resut = sizeString.apply("Entendendo a expressão lambda");
        System.out.println(resut);
    }
}

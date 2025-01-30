package com.github.sfidencio.lambdas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class L5 {
    public static void main(String[] args) {
        BiConsumer<String,List<BigDecimal>> biConsumer = (s, list) -> {
            System.out.println(s);
            list.add(BigDecimal.ONE);
            list.add(BigDecimal.TEN);
            list.add(BigDecimal.ZERO);
            System.out.println(list);
        };

        biConsumer.accept("Lista de números: ", new ArrayList<>());

   //Exemplo de referência de método
        Consumer<List<BigDecimal>> consumer = L5::addNumbers;
        consumer.accept(new ArrayList<>());

    }

    private static void addNumbers(List<BigDecimal> bigDecimals) {
        bigDecimals.add(BigDecimal.ONE);
        bigDecimals.add(BigDecimal.TEN);
        bigDecimals.add(BigDecimal.ZERO);
        System.out.println(bigDecimals);
    }
}

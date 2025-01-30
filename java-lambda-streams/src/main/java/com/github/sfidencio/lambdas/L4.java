package com.github.sfidencio.lambdas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class L4 {
    public static void main(String[] args) {
        Consumer<List<BigDecimal>> consumer = list -> {
            list.add(BigDecimal.ONE);
            list.add(BigDecimal.TEN);
            list.add(BigDecimal.ZERO);
            System.out.println(list);
        };

        consumer.accept(new ArrayList<>());



    }
}

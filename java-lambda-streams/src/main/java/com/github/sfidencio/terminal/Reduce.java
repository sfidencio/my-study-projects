package com.github.sfidencio.terminal;

import java.math.BigDecimal;
import java.util.Map;

public class Reduce {
    public static void main(String[] args) {
        var ages = new int[]{10, 20, 30, 40, 50};
        Map<String, BigDecimal> stocksProduct = Map.of(
                "Apple", new BigDecimal("100.00"),
                "Google", new BigDecimal("200.00"),
                "Microsoft", new BigDecimal("300.00"),
                "Oracle", new BigDecimal("400.00"),
                "IBM", new BigDecimal("4800.00")
        );

        stocksProduct.entrySet().stream().reduce(
                (a, b) -> Map.entry("Total", a.getValue().add(b.getValue()))
        ).ifPresent(System.out::println);

    }
}

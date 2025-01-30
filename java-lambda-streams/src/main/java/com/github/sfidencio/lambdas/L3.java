package com.github.sfidencio.lambdas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class L3 {
    public static void main(String[] args) {
        Supplier<List<BigDecimal>> supplier =
                () -> {
                    List<BigDecimal> list = new ArrayList<>();
                    list.add(BigDecimal.ONE);
                    list.add(BigDecimal.TEN);
                    list.add(BigDecimal.ZERO);
                    return list;
                };

        List<BigDecimal> list = supplier.get();
        System.out.println(list);
    }
}

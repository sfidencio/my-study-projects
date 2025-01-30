package com.github.sfidencio;

import java.math.BigDecimal;
import java.util.Arrays;

public class Application01 {
    public static void main(String[] args) {
        Box<BigDecimal> values = new Box<>();
        values.setItem(new BigDecimal("10.0"));
        System.out.println(values.getItem());
        System.out.println("-----");
        print(new String[]{"Hello", "World"});
        print(new Integer[]{1, 2, 3});
    }

    public static <T> void print(T[] value) {
        Arrays.stream(value).forEach(System.out::println);
    }
}

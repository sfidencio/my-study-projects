package com.github.sfidencio.intermediate;

import java.util.List;

public class Distintict {
    public static void main(String[] args) {
        List<Integer> agesRepeated = List.of(10, 20, 30, 40, 50, 10, 20, 30, 40, 50);
        agesRepeated.stream().distinct().forEach(System.out::println);
    }
}

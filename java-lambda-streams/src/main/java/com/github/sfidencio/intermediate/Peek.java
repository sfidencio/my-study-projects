package com.github.sfidencio.intermediate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Peek {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        List<String> peekedList = list.stream()
                .peek(s -> System.out.println(s))
                .collect(Collectors.toList());
    }
}

package com.github.sfidencio.intermediate;

import java.util.List;

public class Skip {
    public static void main(String[] args) {
        var names = List.of("Alcides", "Alvaro", "Gersom", "Joao", "Jose", "Maria", "Pedro", "Ricardo", "Roberto", "Sergio");
        var skip = names.stream().skip(5);
        skip.forEach(System.out::println);
    }
}

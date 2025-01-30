package com.github.sfidencio.intermediate;

import java.util.List;

public class Filter {
    public static void main(String[] args) {
        List<String> names = List.of("Alcides", "Alvaro", "Gersom", "Joao", "Jose", "Maria", "Pedro", "Ricardo", "Roberto", "Sergio");
        names.stream().filter(
                name-> name.startsWith("A")
        ).forEach(System.out::println);
    }
}

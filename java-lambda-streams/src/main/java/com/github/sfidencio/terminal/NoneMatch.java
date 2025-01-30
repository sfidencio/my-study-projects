package com.github.sfidencio.terminal;

import java.util.List;

public class NoneMatch {
    public static void main(String[] args) {
        List<String> names = List.of("Alcides", "Alvaro", "Gersom", "Joao", "Jose", "Maria", "Pedro", "Ricardo", "Roberto", "Sergio");
        var noneMatch = names.stream().noneMatch(
                name -> name.startsWith("Z")
        );
        System.out.printf("None match: %s\n", noneMatch);
    }
}

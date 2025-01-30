package com.github.sfidencio.terminal;

import java.util.List;

public class AllMatch {
    public static void main(String[] args) {
        var names = List.of("Alcides", "Alvaro");
        var allMatch = names.stream().allMatch(
                s -> s.startsWith("A")
        );
        System.out.printf("All match: %s\n", allMatch);
    }
}

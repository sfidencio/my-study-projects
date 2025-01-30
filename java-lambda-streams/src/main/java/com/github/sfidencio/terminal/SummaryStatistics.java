package com.github.sfidencio.terminal;

import java.util.List;

public class SummaryStatistics {
    public static void main(String[] args) {
        var ages = List.of(10, 20, 30, 40, 50);
        var summaryStatistics = ages
                .stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.printf("Summary statistics: %s\n", summaryStatistics);
    }
}

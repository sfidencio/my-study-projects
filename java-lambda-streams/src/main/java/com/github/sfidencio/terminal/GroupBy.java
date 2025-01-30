package com.github.sfidencio.terminal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupBy {
    public static void main(String[] args) {
        var groupRepeatedNumbers = List.of(1, 2, 3, 4, 5, 1, 2, 3, 5, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 11, 1, 1, 23, 19, 19, 23, 3, 3, 3);
        //Group repeated numbers using stream in terminal operation
        groupRepeatedNumbers.stream()
                .collect(
                        Collectors.groupingBy(integer -> integer)
                ).forEach(
                        (k, v) -> System.out.printf("Number: %d, Count: %d\n", k, v.size())
                );


        System.out.println("************************************");

        //method traditional using for loop
        var groupRepeatedNumbers2 = List.of(1, 2, 3, 4, 5, 1, 2, 3, 5, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 11, 1, 1, 23, 19, 19, 23, 3, 3, 3);
        Map<Integer, Integer> qtdNumbersRepeated = new HashMap<>();

        for (int i = 0; i < groupRepeatedNumbers2.size(); i++) {
            int number = groupRepeatedNumbers2.get(i);
            int count = 0;
            for (int j = 0; j < groupRepeatedNumbers2.size(); j++) {
                if (number == groupRepeatedNumbers2.get(j)) {
                    count++;
                }
            }
            qtdNumbersRepeated.put(number, count);
        }
        qtdNumbersRepeated.forEach((k, v) -> System.out.printf("Number: %d, Count: %d\n", k, v));
    }
}

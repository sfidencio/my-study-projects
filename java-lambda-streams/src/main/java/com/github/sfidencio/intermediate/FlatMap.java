package com.github.sfidencio.intermediate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlatMap {
    public static void main(String[] args) {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"),
                Arrays.asList("e", "f"),
                Arrays.asList("g", "h"),
                Arrays.asList("i", "j")
        );
        List<String> flatMappedList = list.stream()
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());

        System.out.println(flatMappedList);

        System.out.println("******************************************************");

        Map<String,List<Integer>> map = new HashMap<>();
        map.put("a", List.of(1,2,3));
        map.put("b", List.of(4,5,6));
        map.put("c", List.of(7,8,9));

        System.out.println(map);

        //FlatMap
        map.entrySet().stream()
                .flatMap(e->e.getValue().stream())
                .forEach(System.out::println);

        List<Integer> mapFlatMappedList = map.entrySet().stream()
                .flatMap(e->e.getValue().stream())
                .collect(Collectors.toList());

        System.out.println(mapFlatMappedList);

    }
}

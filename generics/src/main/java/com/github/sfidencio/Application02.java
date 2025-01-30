package com.github.sfidencio;

import java.util.List;

import static com.github.sfidencio.Application01.print;

public class Application02 {
    public static void main(String[] args) {
        NumberBox<Integer> values = new NumberBox<>();
        values.setItem(10);
        System.out.println(values.getItem());
        System.out.println("-----");
        print(new String[]{"Hello", "World"});
        print(new Integer[]{1, 2, 3});
        System.out.println("-----");
        printList(List.of("Hello", "World"));
        printList(List.of(1, 2, 3));
        printList(List.of(1.0, 2.0, 3.0));

        //? -> wildcard -> any type of object can be used here
        //Not permitted to add any element to the list, always read-only or receive list of any type
    }

    public static void printList(List<?> list) {
        list.forEach(System.out::println);
    }
}

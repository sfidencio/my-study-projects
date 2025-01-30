package com.github.sfidencio.intermediate;

import java.util.List;

public class DropWhile {
    public static void main(String[] args) {
        var names = List.of("Alcides", "Alvaro", "Gersom", "Joao", "Jose", "Maria", "Pedro", "Ricardo", "Roberto", "Sergio", "Amanda", "Ana", "Antonio");

        //DropWhile é o oposto do takeWhile, ele vai ignorar os elementos que atendem a condição.
        names.stream().dropWhile(
                name-> name.startsWith("A")
        ).forEach(System.out::println);

        System.out.println("--------------------------------------------------");

        //Exemplo de uso do filter
        names.stream().filter(
                name-> name.startsWith("A")
        ).forEach(System.out::println);

    }
}

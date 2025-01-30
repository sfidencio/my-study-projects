package com.github.sfidencio.intermediate;

import java.util.List;

public class TakeWhile {
    public static void main(String[] args) {
        var names = List.of("Alcides", "Alvaro", "Gersom", "Joao", "Jose", "Maria", "Pedro", "Ricardo", "Roberto", "Sergio", "Amanda", "Ana", "Antonio");

        /*
         * Qual a diferença entre o filter e o takeWhile? em termos práticos,
         * o takeWhile é um filtro que para de filtrar quando a condição não é mais atendida.
         * Já o filter, filtra todos os elementos que atendem a condição.
         */

        //Exemplo de uso do takeWhile
        names.stream().takeWhile(
                name-> name.startsWith("A")
        ).forEach(System.out::println);

        System.out.println("--------------------------------------------------");

        //Exemplo de uso do filter
        names.stream().filter(
                name-> name.startsWith("A")
        ).forEach(System.out::println);

    }
}

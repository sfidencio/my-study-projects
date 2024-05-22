package teste05.teste04;

import java.util.List;
import java.util.function.Predicate;

/*
 * Exemplos de uso do flatMap do Stream
 */

public class Application {
    public static void main(String[] args) {

        List<List<List<Integer>>> listaAninhada =
                List.of(
                        List.of(
                                List.of(1, 2, 3),
                                List.of(4, 5, 6),
                                List.of(7, 8, 9)
                        ),
                        List.of(
                                List.of(10, 11, 12),
                                List.of(13, 14, 15),
                                List.of(16, 17, 18),
                                List.of()
                        ),
                        List.of(List.of(19, 20, 21))
                );


        System.out.println(listaAninhada);

        System.out.println("Vamos achatar a lista em uma so..pode?");


        var listaAchatada =
                listaAninhada.stream()
                        .flatMap(primeiroNivel -> primeiroNivel.stream())
                                .toList();
                        //.flatMap(segundoNivel -> segundoNivel.stream())
                        //.toList();

        System.out.println(listaAchatada);

        System.out.println("Checando se existe alguma lista vazia na lista aninhada");

        System.out.println("Existe linha vazia no primeiro nivel?");
        System.out.println(
                listaAninhada.stream()
                        .anyMatch(
                                primeiroNivel-> primeiroNivel.isEmpty()
                        )
        );

        System.out.println();
        System.out.println("Existe linha vazia no segundo nivel?");
        System.out.println(
                listaAninhada.stream()
                        .anyMatch(
                                primeiroNivel-> primeiroNivel.stream().allMatch(
                                        segundoNivel -> segundoNivel.isEmpty()
                                )
                        )
        );


        System.out.println();
        System.out.println("Vamos facilitar? ao inves de descer tantos niveis, vamos usar o flatMap");
        System.out.println("Existe lista vazia em todos os niveis?, lembrando q temos 2 niveis");
        System.out.println(
                listaAninhada.stream()
                        .anyMatch(List::isEmpty)


        );
        System.out.println(
                listaAninhada.stream()
                        .flatMap(List::stream)
                        .anyMatch(List::isEmpty)
        );
    }
}

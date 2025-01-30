import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class D2 {
    public static void main(String[] args) {
        //Exemplo de uso do Mono
        //0 ou 1 elemento
        Mono<String> mono = Mono.just("Reactive Programming with Reactor");
        mono.map(String::toUpperCase).subscribe(System.out::println);

        //Exemplo de uso do Flux
        //0 ou N elementos
        Flux<Integer> numbers = Flux.range(1, 10000);
        numbers.map(n -> n * n)
                .filter(n -> n % 2 == 0)
                .subscribe(System.out::println);

    }
}

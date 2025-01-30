import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class D1 {
    public static void main(String[] args) {

        //Exemplo de uso do Mono
        //0 ou 1 elemento
        Mono<String> mono = Mono.just("Hello, World!");
        mono.subscribe(System.out::println);

        //Exemplo de uso do Flux
        //0 ou N elementos
        Flux<String> flux = Flux.just("Hello", "World");
        flux.subscribe(System.out::println);
    }
}

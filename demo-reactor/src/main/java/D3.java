import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class D3 {
    public static void main(String[] args) {
      //Erros
        Flux<Integer> numbers = Flux.range(1, 10000)
                .map(n -> {
                    if (n == 5) {
                        throw new RuntimeException("Error");
                    }
                    return n;
                }).onErrorReturn(-1);
        numbers.subscribe(System.out::println);

    }
}

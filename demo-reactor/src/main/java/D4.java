import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class D4 {
    public static void main(String[] args) {
        int totalRequests = 100000;

        //Cria um fluxo de números de 1 a 10000
        Flux.range(1, totalRequests)
                .flatMap(requestId -> processRequest(requestId))
                .subscribeOn(Schedulers.parallel())
                .doOnComplete(() -> System.out.println("All requests processed"))
                .subscribe(
                        result -> System.out.println("Result: " + result),
                        error -> System.out.println("Error: " + error.getMessage())
                );

        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        }
    }

    private static Mono<String> processRequest(Integer requestId) {
        return Mono.just("Request processed: " + requestId)
                .delayElement(Duration.ofMillis(10)); //Simula uma operação de I/O
    }
}

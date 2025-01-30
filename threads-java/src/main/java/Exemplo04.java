import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exemplo04 {
    public static void main(String[] args) {
//        Runnable r = () -> System.out.println("Thread em execução!");
//        r.run();



        try (ExecutorService service = Executors.newFixedThreadPool(20)) {
            var retorno = service.submit(() -> System.out.println("Executando..."));
            while (!retorno.isDone()) {
                System.out.println("Aguardando retorno de execucao da Thread!");
            }
            System.out.println("Finalizado!");
        }


    }
}

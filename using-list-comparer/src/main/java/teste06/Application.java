package teste06;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        List<List<List<List<Map<String, List<Object>>>>>> listaMalucamenteAninhadaComMapNoFundoDoPoco =
                List.of(
                        List.of(
                                List.of(
                                        List.of(
                                                Map.of("chave1", List.of("valor1", "valor2")),
                                                Map.of("chave2", List.of("valor3", "valor4"))
                                        ),
                                        List.of(
                                                Map.of("chave3", List.of("valor5", "valor6")),
                                                Map.of("chave4", List.of("valor7", "valor8"))
                                        )
                                ),
                                List.of(
                                        List.of(
                                                Map.of("chave5", List.of("valor9", "valor10")),
                                                Map.of("chave6", List.of("valor11", "valor12"))
                                        ),
                                        List.of(
                                                Map.of("chave7", List.of("valor13", "valor14")),
                                                Map.of("chave8", List.of("valor15", "valor16"))
                                        )
                                )
                        ),
                        List.of(
                                List.of(
                                        List.of(
                                                Map.of("chave9", List.of("valor17", "valor18")),
                                                Map.of("chave10", List.of("valor19", "valor20"))
                                        ),
                                        List.of(
                                                Map.of("chave11", List.of("valor21", "valor22")),
                                                Map.of("chave12", List.of("valor23", "valor24"))
                                        )
                                ),
                                List.of(
                                        List.of(
                                                Map.of("chave13", List.of("valor25", "valor26")),
                                                Map.of("chave14", List.of("valor27", "valor28"))
                                        ),
                                        List.of(
                                                Map.of("chave15", List.of("valor29", "valor30")),
                                                Map.of("chave16", List.of())
                                        )
                                )
                        )
                );

        //Vamos brincar agora, no quarto nivel, 0, 1, 2, 3.. temos a lista de valores com Map cuja chave e chave16, mas com a lista de objetcs vazia
        //Temos que encontrar com algoritimo funcional essa lista vazia


    }
}

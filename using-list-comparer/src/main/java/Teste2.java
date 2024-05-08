import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Teste2 {
    public static void main(String[] args) {
        var parametros = new ArrayList<Parametro>();
        parametros.add(Parametro.builder()
                .parametros(Map.of("param1", List.of("value1")))
                .tipo("type1")
                .build());

        parametros.add(Parametro.builder()
                .parametros(Map.of("param2", List.of("value2")))
                .tipo("type2")
                .build());

        parametros.add(Parametro.builder()
                .parametros(Map.of("param3", List.of("value3")))
                .tipo("type3")
                .build());

        System.out.println(getParametros(parametros));
    }

    public static List<ParametroModelo> getParametros(List<Parametro> lista) {
//        return lista.stream()
//                .map(parametro -> ParametroModelo.builder()
//                        .parameters(parametro.parametros())
//                        .type(parametro.tipo())
//                        .build())
//                .collect(Collectors.toList());

        return lista.stream().map(
                parametro -> {
                    System.out.println("Teste");
                    return new ParametroModelo(parametro.parametros(), parametro.tipo());
                }).toList();
    }
}

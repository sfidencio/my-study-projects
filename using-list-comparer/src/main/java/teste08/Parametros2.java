package teste08;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Parametros2 {
    private Map<String, List> parametros;
    private String tipo;
    private String comparador;
}

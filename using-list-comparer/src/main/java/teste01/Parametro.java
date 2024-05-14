package teste01;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record Parametro(Map<String, List<Object>> parametros,
                        String tipo) {
}

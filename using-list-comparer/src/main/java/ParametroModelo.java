import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record ParametroModelo(Map<String, List<Object>> parameters,
                              String type) {
}

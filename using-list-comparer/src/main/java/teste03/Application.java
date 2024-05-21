package teste03;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Object> lista = List.of("3206", "prime", true);

        for (Object valor : lista) {
            for (DiscoveryTypeFromValue discoveryTypeFromValue : DiscoveryTypeFromValue.values()) {
                if (discoveryTypeFromValue.isType(valor))
                    System.out.println(discoveryTypeFromValue);

            }
        }
    }
}

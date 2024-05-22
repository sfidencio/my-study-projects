package teste08;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException {
        List<Parametros2> parametros2 = List.of(
                new Parametros2(Map.of(
                        "Agencia", List.of("3206", "3207")
                ), "N", "0")
        );

        RegrasContextos regrasContextos = new RegrasContextos();
        regrasContextos.setContexts(parametros2);
        //System.out.println(regrasContextos);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        System.out.println(regrasContextos);
        System.out.println(objectMapper.writeValueAsString(regrasContextos));


    }
}

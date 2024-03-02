# dicas-macetes-ferramentas

> [!IMPORTANT]
> Lista de artigos/dicas:

- Dominando git
    - https://github.com/joshnh/Git-Commands
- Dicas IntelliJ
    - https://www.jetbrains.com/help/idea/mastering-keyboard-shortcuts.html
    - Selecionar todas as ocorrencias de uma palavra: `ctrl + shift + alt + j`

- Dicas Java e Frameworks:
    - [Dicas de como designar um tipo ENUM para deserializar uma string vazia (Spring Boot)](#dicas-de-como-designar-um-tipo-enum-para-deserializar-uma-string-vazia-spring-boot)
      - Cenários de uso:
        - Microserviços que consomem outros microserviços, e que por algum motivo, o microserviço consumido retorna uma
          string vazia, e o microserviço consumidor precisa deserializar essa string vazia para um tipo ENUM.

- Instalação e Configuração do IntelliJ embarcado no WSL2
    - https://dev.to/janetmutua/installing-jetbrains-toolbox-on-ubuntu-527f
    - https://github.com/AppImage/AppImageKit/wiki/FUSE
    - https://dev.to/wesleyotio/configurando-wsl2-com-intellij-2pl7
        - Dica interessante sobre a disponibilização dos atalhos no windows. 
    - https://learn.microsoft.com/en-us/windows/wsl/tutorials/gui-apps#install-support-for-linux-gui-apps
        - Instalar o `x11-apps`.     

- Quer evoluir funcionalidades em uma aplicação por meio de features?
    - https://openfeature.dev/specification/
- Como eu envio a URI do recurso recém-criado via verbo POST, e o status CREATED/201 no springboot?
    ```java
  @PostMapping("/mesas/{id}/reservas")
    @Transactional
    public ResponseEntity<?> reservar(
            @PathVariable(value = "id") Long mesaId,
            @RequestBody ReservaMesaRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        // lógica para reservar a mesa

        URI location = uriBuilder.path("/mesas/{id}/reservas/{reservaId}")
                .buildAndExpand(mesa.getId(), reserva.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    ```
- Neste exemplo, a URI é construída com base no padrão /mesas/{id}/reservas/{reservaId}, onde {id} é o identificador da mesa e {reservaId} é o identificador da reserva. Ao chamar uriBuilder.path(...).buildAndExpand(...).toUri(), a URI é construída substituindo os placeholders pelos valores reais.
Ao retornar ResponseEntity.created(location).build(), você está enviando uma resposta 201 CREATED com o cabeçalho Location contendo a URI do recurso recém-criado.
Esse é um exemplo comum de uso de URI na resposta CREATED no Spring Boot para indicar a localização do recurso criado.

- Como gravar json em um campo do tipo json do banco de dados.
  ```java
    @ColumnTransformer(write = "?::jsonb")
    @Column(name = "field_json", nullable = false, columnDefinition = "jsonb")
    private Map<String, String> valores;
  ```

- Criando o Mapper do que converte Map->Json vice-versa:
  ```java

  @Convert(converter = MapConverter.class)
  private Map<String, String> valores;
 ```

```java
public class MapConverter implements AttributeConverter<Map<String, String>, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
```
  

# Dicas IntelliJ - Manipulação de arquivos json por exemplo

> Pressupomos que precisamos extrair apenas o campo "id" do arquivo abaixo, com ajuda da IDE + Regex podemos fazer isso
> facilmente:

```json
[
  {
    "id": 1,
    "nome": "João",
    "idade": 20
  },
  {
    "id": 2,
    "nome": "Maria",
    "idade": 30
  },
  {
    "id": 3,
    "nome": "José",
    "idade": 40
  }
]
```

> Com arquivo acima aberto na IDE podemos fazer o seguinte:
> - selecione com mouse o campo "id" de um dos objetos, da seguinte forma:
    >
- ![img_1.png](img/img_1.png)
>   - O segredo é, tem que ter um padrão, e esse padrão tem que se repetir em todos os objetos, no caso acima o padrão
      é: `"id".
>   - Caso esse padrão não se repita, não tem como fazer isso, pois a IDE não vai conseguir identificar o padrão.
> - pressione `ctrl + shift + alt + j` para selecionar todas as ocorrencias de "id"
> - Deve ficar da seguinte forma a seleção:
> - ![img_2.png](img/img_2.png)
> - Macete:
    >
- Uma vez que o padrão de seleção foi identificado, então com `shift` pressionado, continue a seleção usando as setas do
  teclado, para cima ou para baixo, até que todas as ocorrencias sejam selecionadas.
> - pressione `ctrl + c` para copiar, e cole em outro arquivo, deve ficar da seguinte forma:
> - ```text
>   "id": 1
>   "id": 2
>   "id": 3
> - ```
> - Pressione `ctrl + r` para abrir a janela de substituição, e no campo "Text to find" digite: `$`, em expressão
    regular, esse caracter indica que a ocorrência está no final da linha e no campo "Replace with" digite por
    exemplo: `;`, ou seja vamos inserir virgula no final.
> - Deve ficar da seguinte forma:
> - ```text
>   "id": 1,
>   "id": 2,
>   "id": 3,
>   ```
    >   Finalmente, selecione todo texto e faça um "join" de linha, pressionando `ctrl + shift + j`, deve ficar da
    seguinte forma:
> - ```text
>   "id": 1, "id": 2, "id": 3,
>   ```
    >   Pronto, agora é só copiar e colar onde precisar.

### Dicas de como designar um tipo ENUM para deserializar uma string vazia (Spring Boot)

> 1. Primeiro crie um tipo ENUM com um atributo do tipo String, e um construtor que recebe esse atributo, exemplo:

```java
 public enum TipoEnum {
    TIPO_1("tipo_1"),
    EMPTY("");

    private String tipo;

    TipoEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
 ```

> 2. Agora crie um deserializer para esse tipo ENUM, que espera uma string vazia da response, exemplo:

```java
@Component
public class TipoEnumDeserializer extends JsonDeserializer<TipoEnum> {
    @Override
    public TipoEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String tipo = jsonParser.getValueAsString();
        if (StringUtils.isEmpty(tipo)) {
            return TipoEnum.EMPTY;
        }
        return TipoEnum.valueOf(tipo);
    }
}
```

> 3. Agora vamos registrar esse deserializer ObjectMapper para ser injetado no contexto, exemplo:

```java
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TipoEnum.class, new TipoEnumDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
```

> 5. Pronto, agora é só usar o tipo ENUM no seu DTO que vai ser feito "binding" na `response`, exemplo:

```java
   public class MyDTO {
    @JsonDeserialize(using = TipoEnumDeserializer.class)
    private TipoEnum tipo;
}
```

> 6. E o tipo ENUM será deserializado corretamente, mesmo que a response venha com uma string vazia.
```json
{
  "tipo": ""
}
```

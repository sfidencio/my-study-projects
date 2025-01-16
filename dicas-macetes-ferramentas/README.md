
dicas-macetes-ferramentas

> [!IMPORTANT]
> Lista de dicas, macetes e ferramentas que podem ser úteis no dia a dia de um desenvolvedor.

- Documentação apache camel
   - Apache Camel é uma poderosa biblioteca de integração que permite a criação de aplicativos usando uma abordagem baseada em rotas.
    
  ```xml
    <dependencies>
        <dependencies>  
        <dependency>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-starter</artifactId>  
        </dependency>  
        <dependency>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-starter-web</artifactId>  
        </dependency>  
        <dependency>  
            <groupId>org.apache.camel</groupId>  
            <artifactId>camel-spring-boot-starter</artifactId>  
        </dependency>  
    </dependencies>
```
    

```java
    import org.apache.camel.builder.RouteBuilder;  
    import org.springframework.stereotype.Component;  

	@Component  
	public class MyRoute extends RouteBuilder {  
	    @Override  
	    public void configure() throws Exception {  
	        from("timer:hello?period=5000")  // Gera um evento a cada 5 segundos  
	            .setBody().simple("Hello from Apache Camel!")  // Define a mensagem a ser enviada  
	            .to("log:info");  // Log a mensagem no console  
	    }  
	}
  ```

```yaml
    camel.springboot.main-run-args=--spring.main.allow-bean-definition-overriding=true  
    logging.level.org.apache.camel=INFO
```
   - https://camel.apache.org/docs/ 	

- Dicas sobre rabbitmq
   - https://www.linkedin.com/pulse/exchanges-do-rabbitmq-otthon-le%C3%A3o-bkmbf/
   - https://www.rabbitmq.com/ 

- Precisa de realizar querys dinâmicas usando spring-data/JPA?
   - https://medium.com/@bubu.tripathy/dynamic-query-with-specification-interface-in-spring-data-jpa-ae8764e32162#:~:text=The%20Specification%20interface%20in%20Spring%20Data%20JPA%20is%20a%20powerful 

- CLI super intuitivo para PgSQL (MacOS) - By Klaus
   - https://www.pgcli.com 

- Leitura de arquivos de configuração em diretórios, mapeando usando spring.config.tree:
   - https://docs.spring.io/spring-boot/reference/features/external-config.html 

- VT (Virtual Threads), fim webflux
   - https://medium.com/@sheywesk/o-fim-do-webflux-no-spring-boot-virtual-threads-java-21-b5a63e20f9ef 

- Operações @Async
   - https://saannjaay.medium.com/how-to-implement-cache-redis-cache-and-async-communication-in-spring-boot-390c1be6f2b7 
  
- Redis Interface Admin
   - https://github.com/joeferner/redis-commander 

- Usando Interable<T> Java 5
   - https://medium.com/javarevisited/iterate-over-any-iterable-in-java-bec78eeeb452 

- Desmistificando os parâmetros de otimização da JVM, gerenciamento de memória e etc.
   - https://deviniciative.wordpress.com/2024/05/25/entendendo-o-modelo-de-memoria-do-java/

- Para cobertura de código podemos usar `codecov` integrado com `gihub actions` ou `travis-ci`

- Usando cronmaker pra calcular crontab (SpringBatch)
  - http://www.cronmaker.com/;jsessionid=node01m85gpyj0h5w8qxpl3yx85h471033613.node0?0 

- Many-to-Many fácil com Spring-Data
  - https://www.bezkoder.com/jpa-many-to-many/
    - `Cuidado`, o Set.of(), List.of(), Map.of(), cria objetos imutáveis.
    - `Cuidado` ao utilizar o Set<> em uma relação many-to-many com mapeamento bi-direcional, pois o mesmo pode eliminar registros do lado da relação que possui o atributo `mappedBy`. Utilize List<>, principalmente quando estamos utilizando apenas duas entidades, para fazer o mapeamento, usando PK composta (FK, FK) -> unique=true.
    - `Dica`: Utilize o LAZY caso queira adotar carregamento tardio, ou EAGLE, caso queria carregar o pai e os filhos em uma única consulta.

- Preservar nome de colunas/tabelas no JPA/hibernate
  - https://www.baeldung.com/hibernate-naming-strategy (Funcional)
    - Esse tutorial aborda diversas maneiras de implementanção, principalmente com crase `, ao invés de \"\", pois no caso do pgsql, se formos gravar cadeia de caractéres que possuem aspas duplas, no caso json por exemplo, teremos problema de parse no dbms.    

- Caso queira gravar `payload` json em um campo do tipo jsonb/json no pgsql
  - https://zjor.medium.com/support-of-jsonb-in-h2-test-dd5113c11baa 
  - https://thorben-janssen.com/persist-postgresqls-jsonb-data-type-hibernate/ (valendo) 
  - https://prateek-ashtikar512.medium.com/how-to-handle-json-in-postgresql-5e2745d5324
  - https://vladmihalcea.com/how-to-map-json-objects-using-generic-hibernate-types/
     
- Diferentes formas de implementação do many-to-many no JPA
  - https://www.baeldung.com/jpa-many-to-many
     
- Implementando JWT Token com springboot
  - https://medium.com/p/98702d6313a5 (Implementando token spring security 6) - atualizado
    - https://medium.com/spring-boot/jwt-refresh-token-spring-security-c5b4646cdbd9 (RefreshToken funcional) 
  - https://github.com/ali-bouali/spring-boot-3-jwt-security/tree/main (funcional) 
  - https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac
    - 0.11.5 
  - https://www.toptal.com/spring/spring-security-tutorial
  - https://hackernoon.com/mastering-jwt-authentication-and-authorization-in-spring-boot-31
  - https://www.baeldung.com/spring-security-sign-jwt-token
  - https://medium.com/spring-boot/spring-boot-3-spring-security-6-jwt-authentication-authorization-98702d6313a5
    - 0.12.5 

- Para avançar a `sequence` no pgsql, utilizando a função interna `nextval`

```sql
insert into user (id,username,password) values (nextval('user.seq'), 'john', md5('1234'));
```


- Setar token JWT via variáveis utilizando postman (Produtividade)
  - https://iroshandu.medium.com/set-bearer-token-as-environment-variable-in-postman-for-all-apis-13277e3ebd78 

- Plugin maven testes de integração
  - Coloque suas classes de teste de integração no diretório src/test/java seguindo o layout de diretório padrão do Maven.
  - Nomeie suas classes de teste de integração com o seguinte padrão: *IT.java ou *ITCase.java.
    
  ```xml
      <!--Plugin para rodar testes de integração -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>${maven-failsafe-plugin.version}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
  ```



- Excelente exemplo integração de springboot + firebase
    - https://github.com/hardikSinghBehl/firebase-integration-spring-boot/tree/main 

- Uso extensivo do RestClient
    - https://docs.spring.io/spring-framework/reference/integration/rest-clients.html


- Utilizando Apache Commons Lang para comparação de valores
    - https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/compare/ComparableUtils.html     

- Instalação SonarQube via docker-compose com ajustes no pom.xml e instalação do sonar-type-nexus
    - https://medium.com/@denis.verkhovsky/sonarqube-with-docker-compose-complete-tutorial-2aaa8d0771d4     
    - https://medium.com/@chiemelaumeh1/install-sonatype-nexus-3-using-docker-compose-setup-nexus-repository-manager-for-node-js-project-47a3c5efe1ee
      
- Github Actions
    - https://ilkerguldali.medium.com/1-4-lets-create-a-spring-boot-app-with-mysql-docker-docker-compose-8acaee3a2c4d 

- Boas práticas docker
    - https://medium.com/@bubu.tripathy/dockerizing-your-spring-boot-application-75bf2c6568d0 

- Continuar com a conexao insegura no chrome, devido erro ou handshake de certificado nao valido:
    - sendCommand(SecurityInterstitialCommandId.CMD_PROCEED)
    - Executar o comando acima no `console` no modo desenvolvedor do browser (F12) 

- Configuração de Logging SpringBoot (Considerando Profile):
    - https://medium.com/codex/spring-boot-logging-da61911ce8e6 
    - https://www.baeldung.com/spring-boot-logging 

- Implementando leitura de variaveis do pom.xml dentro do `application.properties` ou `application.yaml`:
    - Add o plugin abaixo na seção de plugins:
      ```xml
       <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <version>3.3.1</version>
            <configuration>
                <delimiters>
                    <delimiter>@</delimiter>
                </delimiters>
                <useDefaultDelimiters>false</useDefaultDelimiters>
            </configuration>
      </plugin>
      ```
    - Add o recurso abaixo na seção do `build`:
      ```xml
          <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
      ```
    - Sempre lembrar de pegar a versão mais atualizada do plugin:
        - https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-resources-plugin
        - https://docs.spring.io/spring-boot/docs/1.4.x/reference/html/howto-properties-and-configuration.html
    - Abaixo exemplo de uso no `application.yaml`:
      ```yaml
        versao:
          mobile: @versao.mobile@
          api: @versao.api@
      ```
  
- Install `vim` on MacOS
    - Define `vim` default editor in GIT
        - ```bash git config --global core.editor "vim" ```

- Arquitetura Hexagonal
    - https://reflectoring.io/spring-hexagonal/
    - https://www.baeldung.com/hexagonal-architecture-ddd-spring

- Aventurando no GraalVM
    - https://medium.com/codex/optimising-performance-with-graalvm-a-guide-to-migrating-a-spring-boot-project-to-native-image-fbb2dcf5d405 

- [Pilares XP](#pilares-xp)

- [Pilares TDD](#pilares-tdd)

- [Falhas de Serializacao e Deserializacao](#falhas-de-serializacao-e-deserializacao)

- Implementando circuit break em ms springboot
   - https://medium.com/@truongbui95/circuit-breaker-pattern-in-spring-boot-d2d258b75042 

- Pesquisar solução de código em vários repositórios do git, a partir de um único ponto?
   - https://sourcegraph.com/search (indexed)
     
- Configurar Swagger SpringBoot usando SpringDoc (SpringFox Descontinuado)
   - https://www.bezkoder.com/spring-boot-swagger-3/
     
- Dicas MacOS:
   - Instalar SDKMan for MacOS - Gerenciar instalações JAVA
   -  https://sdkman.io/install
   -  Repetição de teclas:
      - Executar no terminal
         - defaults write -g ApplePressAndHoldEnabled -bool false
   - Instalar Shottr (PrintScreen, ScreenShots)
      - https://shottr.cc/ 
   - Trabalhar com Clipboard (Instalar o Maccy)
      - https://maccy.app/
   - Instalar rectangle para gestão de janelas
      - https://rectangleapp.com/
   - Configuração do ohMyzsh + PowerLevel(p10k)
      - [https://github.com/sfidencio/my-study-projects/tree/master/explorando-configuracao-ambiente-windows-wsl2-ubuntu-shell-zshrc (procure](https://v-char.medium.com/now-let-customize-your-native-macos-terminal-with-oh-my-zsh-and-powerlevel10k-b48b9c30d39f)
   - Configurando iTerm (Enable Transparency)
      - https://iterm2.com/
   - Atalhos
      - `Option + E` Acento agudo
      - `Shift + 6` Acento circunflexo
      - `Option + N` Til
      - `Option + C` Cedilha 

- [Instalação e Configuração do IntelliJ embarcado no WSL2](#instalação-e-configuração-do-intellij-embarcado-no-wsl2)
- [Quer evoluir funcionalidades em uma aplicação por meio de features?](#quer-evoluir-funcionalidades-em-uma-aplicação-por-meio-de-features)
- [Como eu envio a URI do recurso recém-criado via verbo POST, e o status CREATED/201 no springboot?](#como-eu-envio-a-uri-do-recurso-recém-criado-via-verbo-post-e-o-status-created201-no-springboot)
- [Como gravar json em um campo do tipo json do banco de dados. (Uso)](#como-gravar-json-em-um-campo-do-tipo-json-do-banco-de-dados-uso)
- [Implementando um Mapper pra converter Map<?,?> para campo tipo json/text, usando JPA/hibernate:](#implementando-um-mapper-pra-converter-map-para-campo-tipo-jsontext-usando-jpahibernate)
- [Dicas Lombok:](#dicas-lombok)
- [Dicas sobre Logging no java](#dicas-sobre-logging-no-java)
- [Lombok não funciona corretamente com MapStruct, quando envolve a feature record do java 17+? então veja o artigo abaixo:](#lombok-não-funciona-corretamente-com-mapstruct-quando-envolve-a-feature-record-do-java-17-então-veja-o-artigo-abaixo)
- [Dicas IntelliJ - Manipulação de arquivos json por exemplo](#dicas-intellij---manipulação-de-arquivos-json-por-exemplo)
- [Dicas de como designar um tipo ENUM para deserializar uma string vazia (Spring Boot)](#dicas-de-como-designar-um-tipo-enum-para-deserializar-uma-string-vazia-spring-boot)
- [Básico do GIT](#básico-do-git)
- [Implementando flyway](#implementando-flyway)
- [Criando projeto springcloud em modulos](#criando-projeto-springcloud-em-modulos)
- Use o jetbrainsToolbox, facita a gestão de ferramentas e permite usar a versão EAP do intelliJ ultimate.
    - https://www.jetbrains.com/toolbox-app/download/download-thanks.html?platform=windows
- [Padrões de URI](#padrões-de-uri)
- [Entendendo Idempotência](#entendendo-idempotência)
- Instale o Git Copilot, e o tema Git Copilot Dark Them no InteliiJ, na seção plugins.
- Considere o uso do SDKMAN para gestão de versões da JDK no java.
    - https://sdkman.io/
- Deploy continuo na AWS via github actions
    - https://www.youtube.com/watch?v=mIuFF_ZP_60
- Gerenciamento usuário com KeyCloak
    - https://www.youtube.com/watch?v=wgdo5I53GQo
- [Dicas GIT](#dicas-git)
- [Paginacao Spring Data](#paginacao-spring-data)
- [Respeitar ordem de execução dos testes unitários no Junit5](#respeitar-ordem-de-execução-dos-testes-unitários-no-junit5)
- Implementando validação no jacoco (SpringBoot)
     - https://www.baeldung.com/jacoco
     - https://www.baeldung.com/jacoco-report-exclude
         - Permite configurar o jacoco via annotation (Exclude) 
   ```xml
      <plugin>
                        <groupId>org.jacoco</groupId>
                        <artifactId>jacoco-maven-plugin</artifactId>
                        <version>0.8.7</version>
                        <executions>
                            <execution>
                                <id>default-prepare-agent</id>
                                <goals>
                                    <goal>prepare-agent</goal>
                                </goals>
                            </execution>
                            <execution>
                                <id>default-report</id>
                                <phase>prepare-package</phase>
                                <goals>
                                    <goal>report</goal>
                                </goals>
                            </execution>
                            <execution>
                                <id>default-check</id>
                                <goals>
                                    <goal>check</goal>
                                </goals>
                                <configuration>
                                    <rules>
                                        <rule>
                                            <element>BUNDLE</element>
                                            <limits>
                                                <limit>
                                                    <counter>COMPLEXITY</counter>
                                                    <value>COVEREDRATIO</value>
                                                    <minimum>0.60</minimum>
                                                </limit>
                                            </limits>
                                        </rule>
                                    </rules>
                                </configuration>
                            </execution>
                        </executions>
                    </plugin>
   ```
- Com a configuração acima, é possível validar a cobertura mínima de código:   
   - Ao executar um `mvn verify`, no cenário acima, teremos uma exigência mínima de 60% de cobertura de código:
      - `Rule violated for bundle api-livros: complexity covered ratio is 0.55, but expected minimum is 0.60`
   
- Load @Configuration based in config property in `YAML`
  ```java
      @Configuration
      @ConditionalOnProperty(name = "myapp.feature.enabled", havingValue = "true")
      public class MyConfiguration {
          // Beans and configurations
      }
  ```

  
- Implementando LogInterceptor

```java
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Request URL: " + request.getRequestURL());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("Response Status: " + response.getStatus());
    }
}

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }
}

```

       


# Instalação e Configuração do IntelliJ embarcado no WSL2
    - https://www.renatofialho.com/blog/intellij-and-wsl-not-a-good-match (tutorial mais completo)
    - https://dev.to/janetmutua/installing-jetbrains-toolbox-on-ubuntu-527f
    - https://github.com/AppImage/AppImageKit/wiki/FUSE
    - https://dev.to/wesleyotio/configurando-wsl2-com-intellij-2pl7
        - Dica interessante sobre a disponibilização dos atalhos no windows. 
    - https://learn.microsoft.com/en-us/windows/wsl/tutorials/gui-apps#install-support-for-linux-gui-apps
        - Instalar o `x11-apps`.     
# Implementação de Spring-HATEOS
    - https://grapeup.com/blog/how-to-build-hypermedia-api-with-spring-hateoas/#

# Quer evoluir funcionalidades em uma aplicação por meio de features?
    - https://openfeature.dev/specification/

# Como eu envio a URI do recurso recém-criado via verbo POST, e o status CREATED/201 no springboot?
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

# Como gravar json em um campo do tipo json do banco de dados. (Uso)
  ```java
    @ColumnTransformer(write = "?::jsonb")
    @Column(name = "field_json", nullable = false, columnDefinition = "jsonb")
    private Map<String, String> valores;
  ```

# Desta maneira, usando o campo do tipo `text`, e possivel recuperar o objeto sem problemas no parse:

```java
    @Column(name = Constantes.TABLE_NAME_FEATURES_FIELD_VALORES, nullable = false, columnDefinition = "text")
    @Convert(converter = MapConverterGlobalValues.class)
    //@ColumnTransformer(write = "?::json")
    private Map<String, String> valores;
```

# Implementando um Mapper pra converter Map<?,?> para campo tipo json/text, usando JPA/hibernate: 

```java

//Uso
@Convert(converter = MapConverter.class)
private Map<String, String> valores;

//Implementação do converter
public class MapConverterGlobalValues implements AttributeConverter<Map<String, String>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
```

# Dicas Lombok:
    - O Lombok é uma biblioteca muito útil para reduzir a verbosidade do código Java, evitando a escrita de getters, setters, construtores, entre outros métodos padrão. Além das funcionalidades básicas, o Lombok oferece recursos avançados que podem ser configurados para atender às necessidades específicas do seu projeto.
Aqui estão algumas configurações avançadas do Lombok que você pode utilizar:

          - @Builder: A anotação @Builder gera um padrão Builder para a classe, permitindo a criação de objetos de forma mais fluente e legível.

          - @SneakyThrows: A anotação @SneakyThrows permite lançar exceções verificadas sem a necessidade de declará-las no método ou no throws clause.

          - @Synchronized: A anotação @Synchronized adiciona sincronização ao método ou bloco de código em que é aplicada.

          - @Cleanup: A anotação @Cleanup é usada para garantir a limpeza de recursos automaticamente, como fechamento de streams.

          - @AllArgsConstructor: A anotação @AllArgsConstructor gera um construtor que inicializa todos os campos da classe.

    - Para habilitar esses recursos avançados do Lombok, você precisa configurar adequadamente o plugin do Lombok em sua IDE e adicionar as dependências corretas em seu projeto. Além disso, é importante estar ciente de como cada recurso funciona e como aplicá-los corretamente em suas classes.

# Dicas sobre Logging no java

> Caso queira visualizar os log's em niveis de DEBUG e TRACE, sem a necessidade de configurar detalhadamente cada pacote, pasta setar no root.
```yaml
logging:
  level:
    # Default é INFO, mas pra facilitar troubleshooting, usa DEBUG e TRACE
    # Principalmente pra checar valores setados no banco de dados
    # Nesse cenário mostra tudo de todos pacotes, nao precisa configurar detalhadamente cada pacote
    # TRACE ja inclui DEBUG e INFO
    root: ${LOG_LEVEL_ROOT:TRACE}
```

# Lombok não funciona corretamente com MapStruct, quando envolve a feature `record` do java 17+? então veja o artigo abaixo:
 - https://springframework.guru/using-mapstruct-with-project-lombok/

# Dicas IntelliJ - Manipulação de arquivos json por exemplo

  - https://www.jetbrains.com/help/idea/mastering-keyboard-shortcuts.html
  - Selecionar todas as ocorrencias de uma palavra: `ctrl + shift + alt + j`
  - Pressupomos que precisamos extrair apenas o campo "id" do arquivo abaixo, com ajuda da IDE + Regex podemos fazer isso facilmente:

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

- Com arquivo acima aberto na IDE podemos fazer o seguinte:
-  selecione com mouse o campo "id" de um dos objetos, da seguinte forma:
  - ![img_1.png](img/img_1.png)
  - O segredo é, tem que ter um padrão, e esse padrão tem que se repetir em todos os objetos, no caso acima o padrão
      é: `"id".
 - Caso esse padrão não se repita, não tem como fazer isso, pois a IDE não vai conseguir identificar o padrão.
 - pressione `ctrl + shift + alt + j` para selecionar todas as ocorrencias de "id"
 - Deve ficar da seguinte forma a seleção:
 - ![img_2.png](img/img_2.png)
 - Uma vez que o padrão de seleção foi identificado, então com `shift` pressionado, continue a seleção usando as setas do
  teclado, para cima ou para baixo, até que todas as ocorrencias sejam selecionadas.
 - pressione `ctrl + c` para copiar, e cole em outro arquivo, deve ficar da seguinte forma:
  ```text
   "id": 1
   "id": 2
   "id": 3
  ```
 - Pressione `ctrl + r` para abrir a janela de substituição, e no campo "Text to find" digite: `$`, em expressão
    regular, esse caracter indica que a ocorrência está no final da linha e no campo "Replace with" digite por
    exemplo: `;`, ou seja vamos inserir virgula no final.
 - Deve ficar da seguinte forma:
  ```text
   "id": 1,
   "id": 2,
   "id": 3,
  ```
 - Finalmente, selecione todo texto e faça um "join" de linha, pressionando `ctrl + shift + j`, deve ficar daseguinte forma:
  ```text
   "id": 1, "id": 2, "id": 3,
  ```
 - Pronto, agora é só copiar e colar onde precisar.

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
### Outro exemplo de conversão de um json/map para objeto request

> 1. Payload no formato de um map

```json
{
	"code": ["Teste1","Teste2", "Teste3"]
}
```

> 2. Deserializador, que converte o map/json para uma lista de objetos do tipo `NumberCustom`

```java
@Component
public class CustomDeserializer extends JsonDeserializer<List<NumberCustom>> {

    public static final Logger log = Logger.getLogger(CustomDeserializer.class.getName());

    @Override
    public List<NumberCustom> deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);

        var list = new ArrayList<NumberCustom>();

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();

            JsonNode valuesNode = entry.getValue();

            log.info(key);

            if (valuesNode.isArray()) {
                valuesNode.forEach(v -> {
                    var object = new NumberCustom(v.asText());
                    list.add(object);
                });
            }
        }
        return list;
    }
}
```

```java
public record NumberCustom(@NotEmpty(message = "Nao e permitido valor vazio!") String value) {
}
```


> 3. Abaixo o controller, e a anotação do deserializador, que aciona o conversor ou mapper

```java
@RestController
public class MyController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@Valid
                         @RequestBody
                         @JsonDeserialize(using = CustomDeserializer.class) List<NumberCustom> numbers) {
        numbers.forEach(System.out::println);
        return null;
    }
}
```

> 4. Abaixo a configuração do springboot que necessário para configurar o deserializador, pois injeta uma instância personalizada do jackson/ObjectMapper

```java
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new CustomDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
```


# Básico do GIT

### Comandos git para associar o repositório local ao repositório remoto
```bash
git remote add origin URL_DO_REPOSITORIO
git remote -v 
git branch -M main
git push -u origin main
```
### Comandos git para atualizar o repositório local com o repositório remoto
```bash
git pull origin main
```
### Comandos git para atualizar o repositório remoto com o repositório local
```bash
git add .
git commit -m "Mensagem do commit"
git push origin main/master
```
### Comandos git para criar uma nova branch
```bash
git checkout -b nome_da_branch
```
### Comandos git para listar as branches
```bash
git branch
```
### Comandos git para mudar de branch
```bash
git checkout nome_da_branch
```
### Comandos git para deletar uma branch
```bash
git branch -d nome_da_branch
```
### Comandos git para deletar uma branch remota
```bash
git push origin --delete nome_da_branch
```

#### Links sobre git
    - https://github.com/joshnh/Git-Commands

### Padrões de Commit(Semântico)
- https://blog.geekhunter.com.br/o-que-e-commit-e-como-usar-commits-semanticos/
- https://luby.com.br/desenvolvimento/software/commits/

Claro! Aqui estão alguns exemplos de como você pode fazer commits semânticos usando o Git:

Adicionar uma nova funcionalidade:
```bash
git add .
```
```bash
git commit -m "feat(login): Adiciona validação de e-mail no formulário de login"
```
Corrigir um bug:
```bash
git add .
```
```bash
git commit -m "fix(api): Corrige erro de rota na API de usuários"
```
Atualizar a documentação:
```bash
git add .
```
```bash
git commit -m "docs(readme): Atualiza instruções de instalação no README"
```
Fazer uma refatoração no código:
```bash
git add .
git commit -m "refactor: Extrai lógica de autenticação para um novo serviço"
```

# Implementando flyway
 -  [Implementando flyway](https://medium.com/hprog99/set-up-flyway-with-spring-boot-1b24b8abe56e)
 -  https://medium.com/hprog99/set-up-flyway-with-spring-boot-1b24b8abe56e
 -  https://www.baeldung.com/database-migrations-with-flyway
 -  https://blog.tericcabrel.com/handle-database-migrations-in-a-springboot-application-with-flyway/
   
```yaml
spring:
  flyway:
    enabled: true
    baseline-on-migrate: true
    #url: jdbc:postgresql://localhost:5432/db
    #user: postgres
    #password: '1234'
    baseline-version: 1
    baseline-description: 'Baseline version'
    locations: classpath:db/migration
    schemas: public
    table: schema_version
    validate-on-migrate: true
```

```xml
         <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
            <version>${flyway.version}</version>
        </dependency>
```

![image](https://github.com/sfidencio/my-study-projects/assets/660615/597247d5-77be-4294-8639-ffc5ca0836d8)

```text
V<version>__<description>.sql for versioned scripts
U<version>__<description>.sql for undo scripts
R__<description>.sql for repeatable scripts
```


# Criando projeto springcloud em modulos

- https://medium.com/@AlexanderObregon/step-by-step-creating-your-first-spring-cloud-application-ef4dcfe277a4
  

# Padrões de URI

Sure! Here are some common URI patterns used in RESTful APIs along with examples:

1. *Resource Collection URI:*
   - Pattern: /resource
   - Example: /users
   - Description: Represents a collection of a specific type of resource.

2. *Resource Element URI:*
   - Pattern: /resource/{id}
   - Example: /users/123
   - Description: Represents a specific element within a collection, identified by its unique identifier.

3. *Nested Resource URI:*
   - Pattern: /resource/{id}/nested-resource
   - Example: /users/123/orders
   - Description: Represents a nested resource related to a specific resource element.

4. *Resource Sub-collection URI:*
   - Pattern: /resource/{id}/sub-resource
   - Example: /users/123/addresses
   - Description: Represents a sub-collection of resources related to a specific resource element.

5. *Resource Action URI (Custom Actions):*
   - Pattern: /resource/{id}/action
   - Example: /users/123/reset-password
   - Description: Represents a custom action or operation that can be performed on a specific resource element.

6. *Search or Filter URI:*
   - Pattern: /resource?parameter=value
   - Example: /users?role=admin
   - Description: Represents a search or filter operation on a collection of resources based on specified criteria.

7. *Paginated Resource URI:*
   - Pattern: /resource?page={page}&size={size}
   - Example: /users?page=1&size=10
   - Description: Represents paginated results for a collection of resources, allowing clients to navigate through large result sets.

These URI patterns provide a structured and consistent way to design the endpoints of your RESTful API. By following these patterns, you can create a clear and intuitive API structure that is easy to understand and use. Remember to choose URI patterns that best fit the resources and operations of your API and document them properly for API consumers.

# Entendendo Idempotência

Os verbos HTTP que são idempotentes são GET, HEAD, PUT e DELETE. Isso significa que esses verbos podem ser executados várias vezes sem alterar o estado do servidor além da primeira requisição. Vou exemplificar o uso desses verbos idempotentes no Spring Boot:

1. *GET*:
   - O verbo GET é usado para recuperar informações do servidor e é idempotente, ou seja, fazer várias solicitações GET para o mesmo recurso não deve alterar o estado do servidor. Um exemplo de uso no Spring Boot seria:

java
@GetMapping("/api/books/{id}")
public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    // Lógica para recuperar um livro com o ID fornecido
    Book book = bookService.getBookById(id);
    return ResponseEntity.ok(book);
}


2. *HEAD*:
   - O verbo HEAD é semelhante ao GET, mas retorna apenas os cabeçalhos da resposta, sem o corpo da resposta. Também é idempotente. Um exemplo no Spring Boot seria semelhante ao método GET acima, mas retornando apenas os cabeçalhos.

3. *PUT*:
   - O verbo PUT é usado para atualizar um recurso no servidor e é idempotente, ou seja, a mesma solicitação PUT pode ser feita várias vezes sem efeitos colaterais. Um exemplo de uso no Spring Boot seria:

java
@PutMapping("/api/books/{id}")
public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
    // Lógica para atualizar o livro com o ID fornecido
    Book book = bookService.updateBook(id, updatedBook);
    return ResponseEntity.ok(book);
}


4. *DELETE*:
   - O verbo DELETE é usado para remover um recurso do servidor e é idempotente, ou seja, excluir um recurso várias vezes não deve alterar o estado do servidor após a primeira requisição. Um exemplo de uso no Spring Boot seria:

java
@DeleteMapping("/api/books/{id}")
public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    // Lógica para excluir o livro com o ID fornecido
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
}


Esses são exemplos de como os verbos HTTP idempotentes (GET, HEAD, PUT e DELETE) podem ser utilizados no Spring Boot para realizar operações de leitura, atualização e exclusão de recursos de forma segura e consistente, sem causar efeitos colaterais indesejados no servidor.
O método POST no protocolo HTTP não é considerado idempotente devido à sua natureza de criação ou modificação de recursos no servidor. A característica de idempotência de um método HTTP significa que realizar a mesma operação várias vezes produzirá o mesmo resultado, sem efeitos colaterais adicionais além da primeira requisição.

Aqui estão algumas razões pelas quais o método POST não é idempotente:

1. *Criação de Recursos*: O método POST é comumente usado para criar novos recursos no servidor. Cada vez que uma requisição POST é feita com os mesmos dados, um novo recurso é criado no servidor, resultando em um estado diferente a cada vez.

2. *Efeitos Colaterais*: Uma requisição POST pode ter efeitos colaterais, como a geração de um identificador único para o recurso criado, a atualização de contadores ou a execução de ações específicas associadas à criação do recurso. Esses efeitos colaterais podem tornar as requisições POST não idempotentes.

3. *Operações de Modificação*: Além da criação de recursos, o método POST também pode ser usado para realizar operações de modificação em recursos existentes, como atualizações parciais ou a execução de ações específicas. Essas operações podem alterar o estado do recurso a cada requisição.

4. *Segurança e Integridade dos Dados*: Em alguns casos, é importante que certas operações não sejam idempotentes para garantir a segurança e a integridade dos dados. Por exemplo, uma transação financeira não deve ser idempotente para evitar cobranças duplicadas.

Em resumo, o método POST no protocolo HTTP não é idempotente porque cada requisição POST pode resultar em uma mudança de estado no servidor, seja criando um novo recurso, modificando um recurso existente ou realizando ações específicas associadas à requisição. Portanto, é importante ter cuidado ao usar o método POST para garantir que as operações realizadas sejam apropriadas e não causem efeitos colaterais indesejados.

# Dicas GIT
https://www.horadecodar.com.br/2021/07/23/como-desfazer-um-git-merge-no-repositorio-local/ 

### Como remover arquivos da área de stage 
```bash
git status 
git reset 
```

### script .bashrc para log in mais rápido nas rotinas git:  
```bash
var1=" https://USUARIO:SENHA@github/PROJETO.git" 
```

### Update pode ser qualquer nome, função do git fetch e git pull 
```bash
alias update='git fetch $var1 && git pull $var1' 
```

push pode ser qualquer nome, função do git push 
```bash
alias push='git push $var1' 
```

like 1 
 
### Como desfazer modificações não salvas 

```bash
git status 
git reset 
git clean -df 
git checkout -- . 
```

### Como desfazer o último commit  - desfazer último commit sem desfazer as modificações nos arquivos: 

```bash
git status 
git reset --soft HEAD~1 
```

### Como deletar commits e também 

### Modificações nos arquivos 
### Voltar o projeto ao estado de um dado commit (deletar commits e alterações posteriores a esse commit) 

```bash
git status 
git reset --hard <código do commit> 
```

### ATENÇÃO: ação destrutiva! 


### Como atualizar o repositório local em relação ao remoto 

```bash
git status 
git pull <nome do remote> <nome do branch> 
```

### Como resolver push rejeitado 
### Não é permitido enviar um push se seu repositório local está atrasado em 
### Relação ao histórico do repositório remoto! Por exemplo: 

```bash
git pull <nome do remote> <nome do branch> 
```

### Você tem que atualizar o repositório local: 

### Resolvendo conflito 

 - Analise o código fonte 
 - Faça as edições necessárias 
 - Faça um novo commit 

 

### Como sobrescrever um histórico no Github 
```bash
git push -f <nome do remote> <nome do branch> 
```

### ATENÇÃO: ação destrutiva! 
   - Como apontar o projeto para outro 
   - repositório remoto 

```bash
git remote set-url origin git@github.com:seuusuario/seurepositorio.git 
```

### Stash - Backup de Workspace  
```bash
git stash push -m "my_stash" 
git stash list 
git stash pop stash@{n} 
git stash apply stash@{n} -> aplicar stash e manter salvo 
git stash apply my_stash 
git restore . 
```

   - https://ohshitgit.com/ 

### Como alterar mensagens antigas de commit 
```bash
git rebase -i HEAD~n 
```

### Onde n e o numero de commits q deseja voltar 
 
### Para altera o commit inicial e todos os outros 
```bash
git rebase -i --root  
```

### Continuar edição dos commits 
```bash
git rebase --edit-todo 
 ```

### Vai aparecer "pick"...e so alterar para "reword"..o texto do commit que deseja alterar,..fazendo isso salve, (ESC^wq!).. Logo, vai ser aberto o arquivo do commit especifico que colocaste "reword"..entao proceda com a alteracao. 
 

### Caso queira alterar, somente o ultimo commit,..utilizo o --amend no commit assim: 

git commit --amend  

### Voltar commit preservando as modificações atuais. 
```bash
git log –oneline  
```

### Pega o hash md5 do commit desejado 

```bash
git reset –mixed HASH_COMMIT  
git add . && git commit –m "NUMERO_CAD: ...." --amend –no-edit 0362804
```

### Apagando e persistindo credenciais de acessos git 
```bash
git config --global --unset credential.helper 
git config --global credential.helper store
```

# Vinculando repository GIT e empurrando codigo

```bash
git init
git remote add origin <URL_do_repositorio_remoto>
git add .
git commit -m "Mensagem do commit"
git push -u origin main
```

# Configurando TOKEN no github actions

Para configurar um token de acesso pessoal do GitHub no GitHub Actions, você pode adicionar o token como uma variável de ambiente no seu fluxo de trabalho. Isso permite que o GitHub Actions use o token para autenticação ao interagir com o repositório remoto. Aqui está um exemplo de como você pode fazer isso:

Crie um token de acesso pessoal no GitHub:

Acesse o GitHub e vá para "Settings" (Configurações) -> "Developer settings" (Configurações do desenvolvedor) -> "Personal access tokens" (Tokens de acesso pessoal).
Clique em "Generate new token" (Gerar novo token), dê um nome ao token, selecione as permissões necessárias e clique em "Generate token" (Gerar token).
Copie o token gerado.
Adicione o token como uma variável de ambiente no GitHub Actions:

Vá para o seu repositório no GitHub e acesse a aba "Settings" (Configurações).
No menu lateral, clique em "Secrets" (Segredos) e depois em "New repository secret" (Novo segredo de repositório).
No campo "Name" (Nome), digite um nome para o segredo (por exemplo, ACCESS_TOKEN).
No campo "Value" (Valor), cole o token de acesso pessoal que você gerou.
Clique em "Add secret" (Adicionar segredo) para salvar o token como uma variável de ambiente.
Use o token no seu fluxo de trabalho:

No seu arquivo YAML do GitHub Actions, você pode acessar o token como uma variável de ambiente. Por exemplo, para configurar o token como a senha para autenticação HTTPS, você pode adicionar o seguinte passo:
- name: Set up Git
  env:
    ACCESS_TOKEN: ${{ secrets.ACCESS_TOKEN }}
  run: |
    git config --global credential.helper store
    echo "https://$ACCESS_TOKEN@github.com" > ~/.git-credentials
Dessa forma, o GitHub Actions usará o token de acesso pessoal configurado como uma variável de ambiente para autenticação ao interagir com o repositório remoto. Certifique-se de proteger o token de acesso pessoal e não compartilhá-lo publicamente.


# Outra forma de configurar o token para uso do github actions



Para autenticar o GitHub Actions e permitir que seus workflows tenham acesso ao seu repositório do GitHub, você pode usar tokens de acesso pessoal ou chaves SSH. Aqui estão algumas opções de autenticação para o GitHub Actions:

Token de acesso pessoal:
Crie um token de acesso pessoal no GitHub com as permissões necessárias para acessar o repositório.
No GitHub, vá em "Settings" -> "Developer settings" -> "Personal access tokens" -> "Generate new token".
Copie o token gerado e armazene-o em um local seguro.
No seu workflow do GitHub Actions, você pode usar o token de acesso pessoal como uma variável de ambiente para autenticação.
Exemplo de como usar um token de acesso pessoal em um workflow:

name: My Workflow

on: [push]

jobs:
  my_job:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v2
        with:
          token: ${{ secrets.GITHUB_TOKEN }}
Chave SSH:
Adicione uma chave SSH ao GitHub que tenha acesso ao seu repositório.
No seu workflow do GitHub Actions, você pode configurar a chave SSH para autenticação.
Exemplo de como usar uma chave SSH em um workflow:

name: My Workflow

on: [push]

jobs:
  my_job:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v2
        with:
          ssh-key: ${{ secrets.SSH_PRIVATE_KEY }}
Lembre-se de armazenar informações sensíveis, como tokens de acesso pessoal ou chaves SSH, em secrets no GitHub para manter suas credenciais seguras. Você pode configurar esses secrets em "Settings" -> "Secrets" do seu repositório.

# Versionamento

A semântica de versionamento é um sistema padronizado para atribuir números de versão a software, de modo que os desenvolvedores e usuários possam entender facilmente o impacto das atualizações. A versão de um software é geralmente representada por três números separados por pontos, como X.Y.Z, onde:

X é a versão principal (major): Indica grandes mudanças no software que podem não ser compatíveis com versões anteriores. Geralmente, inclui novos recursos, alterações significativas na funcionalidade ou mudanças na API que podem quebrar a compatibilidade com versões anteriores.

Y é a versão secundária (minor): Indica adições de funcionalidades ou melhorias que são compatíveis com versões anteriores. Geralmente, inclui novos recursos ou melhorias que não quebram a compatibilidade com versões anteriores.

Z é a versão de correção (patch): Indica pequenas correções de bugs ou atualizações que não introduzem novas funcionalidades e são compatíveis com versões anteriores. Geralmente, inclui correções de bugs, atualizações de segurança ou pequenas melhorias que não afetam a compatibilidade.

Além disso, a semântica de versionamento define algumas regras adicionais:

Quando você faz uma mudança na versão principal (X), você deve redefinir as versões secundária (Y) e de correção (Z) para zero.
Ao fazer uma mudança na versão secundária (Y), você deve redefinir a versão de correção (Z) para zero.
As versões de correção (Z) podem ser incrementadas sempre que houver correções de bugs ou atualizações menores.
Por exemplo, se um software tem a versão 1.2.3 e uma nova funcionalidade é adicionada sem quebrar a compatibilidade com versões anteriores, a versão pode ser atualizada para 1.3.0. Se houver uma correção de bug nessa versão, a versão pode ser atualizada para 1.3.1.

O uso da semântica de versionamento ajuda a comunicar claramente o impacto das atualizações de software e a garantir que os desenvolvedores e usuários entendam como as mudanças afetam a compatibilidade e as funcionalidades do software.


# Básico de GITFLOW

O Git Flow é uma metodologia popular para gerenciar branches em projetos de software, especialmente útil em ambientes que seguem um ciclo de lançamento regular. Ele define uma estrutura rigorosa baseada em branches para gerenciar features, releases e hotfixes, facilitando a colaboração em equipe e o desenvolvimento paralelo de funcionalidades.

Aqui está um guia passo a passo sobre como instalar e começar a usar o Git Flow no macOS:

Instalação do Git Flow
Instale o Homebrew: Se você ainda não tem o Homebrew (um gerenciador de pacotes para macOS), você pode instalá-lo executando o seguinte comando no Terminal:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Instale o Git Flow: Uma vez que o Homebrew esteja instalado, você pode instalar o Git Flow usando o Homebrew com o seguinte comando:

```bash
brew install git-flow
```

Isso instalará o Git Flow e suas dependências.

Inicialização do Git Flow em um Repositório
Depois de instalar o Git Flow, você precisa inicializá-lo em seu repositório Git local. Aqui está como você pode fazer isso:

Navegue até o seu repositório local: Use o comando cd para mudar para o diretório do seu repositório local.

```bash
cd /caminho/para/seu/repositorio
```

Inicialize o Git Flow: Execute o seguinte comando para inicializar o Git Flow no repositório. Isso configurará os branches necessários para o modelo Git Flow.

```bash
git flow init
```

Durante a inicialização, o Git Flow perguntará sobre os nomes dos branches que serão usados para desenvolvimento, features, releases, hotfixes e suporte. Você pode pressionar Enter para aceitar os padrões ou especificar outros nomes se desejar.

Uso Básico do Git Flow
Aqui estão alguns comandos básicos do Git Flow para começar:

Iniciar uma nova feature: Para começar a trabalhar em uma nova feature:

```bash
git flow feature start NOME_DA_FEATURE
```

Finalizar uma feature: Quando você terminar o desenvolvimento de uma feature e estiver pronto para mesclá-la no branch de desenvolvimento:

```bash
git flow feature finish NOME_DA_FEATURE
```

Iniciar uma release: Para preparar uma nova versão do software:

```bash
git flow release start VERSAO
```

Finalizar uma release: Para completar a release, o que mescla as mudanças no branch main e também no branch de desenvolvimento:

```bash
git flow release finish VERSAO
```

Iniciar um hotfix: Para fazer correções críticas em produção:

```bash
git flow hotfix start NOME_DO_HOTFIX
```

Finalizar um hotfix: Para finalizar o hotfix, o que mescla as mudanças no branch main e no branch de desenvolvimento:

```bash
git flow hotfix finish NOME_DO_HOTFIX
```

Não é comum criar tags para versões na branch develop no GitFlow. A branch develop geralmente é utilizada para integração contínua e desenvolvimento de novas funcionalidades.

As tags são geralmente criadas para marcar versões estáveis e lançamentos na branch master, que representa a versão de produção do software.

No entanto, se você deseja marcar versões específicas na branch develop para facilitar o controle de versões e referências futuras, você pode criar tags para isso. A decisão de criar tags na branch develop deve ser baseada nas necessidades específicas do seu fluxo de trabalho e equipe de desenvolvimento.

# Paginacao Spring Data
![image](https://github.com/sfidencio/my-study-projects/assets/660615/23d09292-31ff-44ef-a0d6-2cd0f34301aa)

![image](https://github.com/sfidencio/my-study-projects/assets/660615/1e726608-241d-4e68-b6c2-a8cb4ddb2310)

![image](https://github.com/sfidencio/my-study-projects/assets/660615/28ed5092-986b-4f9c-9b59-c330448549c1)

![image](https://github.com/sfidencio/my-study-projects/assets/660615/8de31309-ad8c-4202-a9b4-4c73dbe27ab3)

> Observe que o `findByTituloContainingIgnoreCase`, pode incluir mais campos concatenando com `And` ou `Or`, temos essa flexibilidade usando QueryMethod.


# Respeitar ordem de execução dos testes unitários no Junit5

```java

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClassXPTOTest {

   @Test
   @Order(1)
   @DispplayName("My first method execution")
   void test1() {
     ....
   }

}

```


# Pilares XP

😀


A metodologia XP (Extreme Programming) é uma abordagem ágil de desenvolvimento de software que enfatiza a colaboração, a comunicação, a simplicidade e a adaptação a mudanças. Aqui estão algumas práticas e princípios do XP que você pode aplicar ao programar em Java:

### 1. **Programação em Pares (Pair Programming):**
   - Trabalhe em equipe com outro programador para escrever código em conjunto.
   - Compartilhe conhecimento, revise o código um do outro e resolva problemas em conjunto.

### 2. **Test-Driven Development (TDD):**
   - Escreva testes automatizados antes de implementar o código.
   - Refatore e melhore o código continuamente para passar nos testes.

### 3. **Integração Contínua (Continuous Integration):**
   - Integre o código frequentemente para detectar problemas rapidamente.
   - Utilize ferramentas de integração contínua, como Jenkins ou Travis CI.

### 4. **Design Simples (Simple Design):**
   - Mantenha o código simples e fácil de entender.
   - Refatore o código regularmente para eliminar duplicação e manter a coesão.

### 5. **Refatoração (Refactoring):**
   - Melhore a estrutura do código sem alterar seu comportamento.
   - Identifique e corrija código duplicado, complexo ou mal organizado.

### 6. **Propriedade Coletiva do Código (Collective Code Ownership):**
   - Todos os membros da equipe são responsáveis pelo código e podem modificá-lo.
   - Encoraje a colaboração e revisão de código entre os membros da equipe.

### 7. **Iterações Curtas (Short Iterations):**
   - Trabalhe em iterações curtas e entregue valor de forma incremental.
   - Receba feedback regularmente e ajuste o plano conforme necessário.

### 8. **Comunicação Efetiva:**
   - Mantenha uma comunicação clara e aberta com os membros da equipe.
   - Realize reuniões curtas diárias (stand-ups) para compartilhar progresso e identificar obstáculos.

Ao aplicar essas práticas e princípios do XP ao programar em Java, você pode melhorar a qualidade do código, aumentar a colaboração da equipe e entregar valor de forma mais eficiente. Lembre-se de adaptar as práticas do XP de acordo com as necessidades e contexto do seu projeto.

# Pilares TDD
    - https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html
    - https://junit.org/junit5/docs/snapshot/user-guide/index.html#overview-getting-started-example-projects
    - https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html
    - https://www.valuehost.com.br/blog/testes-unitarios/
    - https://www.freecodecamp.org/portuguese/news/como-testar-servicos-endpoints-e-repositorios-com-o-springboot/

### Conceitos

Trata-se da verificação da menor parte testável de um software. Se o código for desenvolvido em uma linguagem que suporta um paradigma funcional, por exemplo, a menor parte será qualquer função. Já se tiver base na orientação a objetos, será um método de seu objeto/classe.

### Boas Práticas
Para escrever testes com qualidade, precisamos ter em mente três ideias centrais:

Testes precisam ser confiáveis.

Seus testes precisam dar a certeza de que eles não possuem bugs.

Testes precisam ser legíveis.

Os testes devem indicar claramente o que está acontecendo à primeira vista. Um teste que não dá pra saber o que está sendo testado não serve para nada.

Testes precisam ser sustentáveis

Os testes precisam seguir a escalabilidade do software que eles testam. Idealmente os testes devem ser imutáveis, portanto é importante garantir que eles se comportem da forma intencionada conforme o código é escalado

### Primeiro Teste deve Falhar (TDD)

Escreva um teste que falhe e faça ter sucesso com o código de produção, assim seus testes sempre devem ser escritos antes dos métodos de produção.
É muito comum "furar" o TDD quando a implementação é algo muito simples. A verdade é que "furar" o TDD é uma má prática e acaba deixando o software vulnerável a erros que poderiam ter sido evitados. Isso auxilia a criação de testes confiáveis já que você sempre vai ver seu teste falhar e ter sucesso.

### Nomes Auto Explicativos
Código de testes deve ser mais legível que código de produção, então não há necessidade de nomear os métodos de teste de forma minimalista.
Prefira nomear os testes da forma que fique o mais claro possível o que o teste está fazendo, mesmo que o nome fique grande e/ou não siga convenções nominais de métodos.

Ex:  

```java
    @Test
    @DisplayName("Dado que o pedido nao existe, quando tentar realizar pedido informando uma quantidade maior que o estoque disponivel, entao o sistema deve impedir o lancamento do pedido retornado erro")
    void DadoQuePedidoNaoExisteQuandoTentarRealizarPedidoInformandoQuantidadeMaiorEstoqueDisponivelEntaoSistemaDeveImpedirLancamentoPedidoRetornandoErro(){
        ...
    }
```

### Rodar Varias Vezes
Teste antes de codificar, após codificar e teste mais uma vez após refatorar o código, não importa o quão pequena for a alteração.
Adotando esse tipo de prática, você assegura que seu código não vai quebrar em nenhum momento e passar despercebido.

### Teste uma Coisa por Vez
Existem ferramentas de Teste Unitário que permitem que os métodos de teste tenham mais de um assert em si.
Isso é considerado uma má prática porque prejudica a clareza do teste, aumenta a chance de ter bugs nos seus testes e torna debugar mais trabalhoso.

Teste uma coisa só por vez.


### Não Insira Lógica nos Testes
Testes não devem conter lógica.
Se o seu teste possui um if ou switch é porque você, provavelmente, está testando mais de uma coisa, e aumenta muito a chance de ter bugs no seu código de teste.

### Simplicidade
Quanto mais simples for a implementação, mais fácil e melhor será de manter em produção.
Isso quer dizer que a maioria das aplicações funcionam melhor quando são mantidas simples ao invés de desnecessariamente complexas.

### Testes Independentes
Cada teste deve executar independentemente de outros.
Haver dependências entre os testes os tornam mais propícios a bugs com a introdução de novos testes.

### Mantenha Testes Antigos Inalterados
Evite alterar ou remover qualquer teste que já esteja passando.
A grande vantagem de utilizar o Teste Unitário, e por consequência o TDD, é a manutenção do código de testes que é executado após cada alteração no código de produção. Alterar ou remover testes que funcionam faz perder totalmente o propósito dos testes que foram construídos.

### Ciclo de Desenvolvimento Red, Green, Refactor (TDD)
Red — o desenvolvedor cria um teste que inicialmente não passará;
ele adiciona a nova funcionalidade ao código;
Green — o teste passa;
Refactor — é feita a refatoração do código;
passa para o próximo teste.
Esse tipo de estratégia promove um feedback rápido sobre essa nova funcionalidade, além de dar um retorno sobre a possível quebra de outras funcionalidades do sistema. Dessa forma, o desenvolvedor ganha muito mais segurança para fazer as refatorações e para adicionar funcionalidades.

### Anatomia
Os testes devem ser criados levando em consideração que dadas tais condições (arrange), ao executar tal ação (act), tais resultados devem ser retornados (assert). Isso é chamado de AAA onde:

Arrange: são definidos os parâmetros de entrada do teste.
Act: ação executada com os parâmetros.
Assert: validação dos resultados gerados pela ação.
Esta forma de desenvolver o teste unitário é bem intuitiva, deixando claro cada passo do teste.

# Falhas de Serializacao e Deserializacao

- Em caso de falhas na serialização de um objeto java no spring, deve criar uma classe de configuração com um método @Bean que retorne uma instância personalizada do ObjectMapper onde desabilita ou habilita certas  configurações, exemplo:

    - SerializationFeature.WRITE_NULL_MAP_VALUES, false: Esta configuração indica que o ObjectMapper não deve incluir valores nulos ao serializar um objeto Java para JSON. Ou seja, se um campo de um objeto for nulo, ele não será incluído no JSON resultante.

    - SerializationFeature.FAIL_ON_EMPTY_BEANS, false: Com esta configuração, o ObjectMapper não irá falhar se encontrar um objeto Java vazio durante a serialização. Em vez disso, ele simplesmente ignorará o objeto vazio e continuará o processo de serialização.

    - DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false: Ao deserializar um JSON para um objeto Java, esta configuração indica que o ObjectMapper não deve falhar se encontrar propriedades desconhecidas no JSON que não correspondam às propriedades do objeto Java.

    - DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true: Com esta configuração, o ObjectMapper irá interpretar uma string vazia como nula durante o processo de deserialização. Ou seja, se um campo em um JSON estiver vazio, ele será considerado nulo ao ser convertido para um objeto Java.
      
```java 
@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return objectMapper;
    }
} 
```



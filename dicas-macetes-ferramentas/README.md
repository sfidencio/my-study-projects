# 💡 dicas-macetes-ferramentas

> [!IMPORTANT]
> Lista de dicas, macetes e ferramentas que podem ser úteis no dia a dia de um desenvolvedor.

---

## 🗂️ Índice

- [🟧 Como usar o LocalStack para emular serviços AWS localmente](#como-usar-o-localstack-para-emular-serviços-aws-localmente)
- [🟦 Framework Dinâmico com Spring Data JPA e Specifications](#framework-dinâmico-com-spring-data-jpa-e-specifications)
- [🐪 Documentação Apache Camel](#documentação-apache-camel)
- [🐰 Dicas sobre RabbitMQ](#dicas-sobre-rabbitmq)
- [🐘 CLI super intuitivo para PgSQL (MacOS)](#cli-super-intuitivo-para-pgsql-macos)
- [📂 Leitura de arquivos de configuração em diretórios](#leitura-de-arquivos-de-configuração-em-diretórios)
- [🧵 VT (Virtual Threads), fim webflux](#vt-virtual-threads-fim-webflux)
- [⚡ Operações @Async](#operações-async)
- [🟥 Redis Interface Admin](#redis-interface-admin)

---

## 🐰 Dicas sobre RabbitMQ

- [Exchanges do RabbitMQ (LinkedIn)](https://www.linkedin.com/pulse/exchanges-do-rabbitmq-otthon-le%C3%A3o-bkmbf/)
- [Site oficial RabbitMQ](https://www.rabbitmq.com/)

---

## 🐘 CLI super intuitivo para PgSQL (MacOS) - By Klaus

- [pgcli.com](https://www.pgcli.com)

---

## 📂 Leitura de arquivos de configuração em diretórios, mapeando usando spring.config.tree

- [Spring Boot External Config](https://docs.spring.io/spring-boot/reference/features/external-config.html)

---

## 🧵 VT (Virtual Threads), fim webflux

- [O fim do webflux no Spring Boot (Medium)](https://medium.com/@sheywesk/o-fim-do-webflux-no-spring-boot-virtual-threads-java-21-b5a63e20f9ef)

---

## ⚡ Operações @Async

- [Como implementar cache, redis cache e comunicação assíncrona no Spring Boot (Medium)](https://saannjaay.medium.com/how-to-implement-cache-redis-cache-and-async-communication-in-spring-boot-390c1be6f2b7)

---

## 🟥 Redis Interface Admin

- [Redis Commander (GitHub)](https://github.com/joeferner/redis-commander)

---

## Usando Interable<T> Java 5

- [Iterate over any Iterable in Java (Medium)](https://medium.com/javarevisited/iterate-over-any-iterable-in-java-bec78eeeb452)

---

## Desmistificando os parâmetros de otimização da JVM, gerenciamento de memória e etc.

- [Entendendo o modelo de memória do Java (Deviniciative)](https://deviniciative.wordpress.com/2024/05/25/entendendo-o-modelo-de-memoria-do-java/)

---

## Para cobertura de código podemos usar `codecov` integrado com `gihub actions` ou `travis-ci`

---

## Usando cronmaker pra calcular crontab (SpringBatch)

- [Cronmaker](http://www.cronmaker.com/;jsessionid=node01m85gpyj0h5w8qxpl3yx85h471033613.node0?0)

---

## Many-to-Many fácil com Spring-Data

- [JPA Many-to-Many (Bezkoder)](https://www.bezkoder.com/jpa-many-to-many/)
  - `Cuidado`, o Set.of(), List.of(), Map.of(), cria objetos imutáveis.
  - `Cuidado` ao utilizar o Set<> em uma relação many-to-many com mapeamento bi-direcional, pois o mesmo pode eliminar registros do lado da relação que possui o atributo `mappedBy`. Utilize List<>, principalmente quando estamos utilizando apenas duas entidades, para fazer o mapeamento, usando PK composta (FK, FK) -> unique=true.
  - `Dica`: Utilize o LAZY caso queira adotar carregamento tardio, ou EAGLE, caso queria carregar o pai e os filhos em uma única consulta.

---

## Preservar nome de colunas/tabelas no JPA/hibernate

- [Hibernate Naming Strategy (Baeldung)](https://www.baeldung.com/hibernate-naming-strategy) (Funcional)
  - Esse tutorial aborda diversas maneiras de implementanção, principalmente com crase `, ao invés de \"\", pois no caso do pgsql, se formos gravar cadeia de caractéres que possuem aspas duplas, no caso json por exemplo, teremos problema de parse no dbms.

---

## Caso queira gravar `payload` json em um campo do tipo jsonb/json no pgsql

- [Support of jsonb in H2 (Zjor)](https://zjor.medium.com/support-of-jsonb-in-h2-test-dd5113c11baa)
- [Persist PostgreSQL's jsonb data type (Thorben Janssen)](https://thorben-janssen.com/persist-postgresqls-jsonb-data-type-hibernate/) (valendo)
- [How to handle JSON in PostgreSQL (Prateek Ashtikar)](https://prateek-ashtikar512.medium.com/how-to-handle-json-in-postgresql-5e2745d5324)
- [How to map JSON objects using generic Hibernate types (Vlad Mihalcea)](https://vladmihalcea.com/how-to-map-json-objects-using-generic-hibernate-types/)

---

## Diferentes formas de implementação do many-to-many no JPA

- [JPA Many-to-Many (Baeldung)](https://www.baeldung.com/jpa-many-to-many)

---

## Implementando JWT Token com springboot

- [Implementando token spring security 6 (Medium)](https://medium.com/p/98702d6313a5) - atualizado
  - [RefreshToken funcional (Medium)](https://medium.com/spring-boot/jwt-refresh-token-spring-security-c5b4646cdbd9)
- [spring-boot-3-jwt-security (GitHub)](https://github.com/ali-bouali/spring-boot-3-jwt-security/tree/main) (funcional)
- [Implement jwt authentication in a spring boot 3 application (Medium)](https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac)
  - 0.11.5
- [Spring Security Tutorial (Toptal)](https://www.toptal.com/spring/spring-security-tutorial)
- [Mastering JWT Authentication and Authorization in Spring Boot (Hackernoon)](https://hackernoon.com/mastering-jwt-authentication-and-authorization-in-spring-boot-31)
- [Sign JWT Token (Baeldung)](https://www.baeldung.com/spring-security-sign-jwt-token)
- [Spring Boot 3 + Spring Security 6: JWT Authentication & Authorization (Medium)](https://medium.com/spring-boot/spring-boot-3-spring-security-6-jwt-authentication-authorization-98702d6313a5)
  - 0.12.5

---

## Para avançar a `sequence` no pgsql, utilizando a função interna `nextval`

```sql
insert into user (id,username,password) values (nextval('user.seq'), 'john', md5('1234'));
```

---

## Setar token JWT via variáveis utilizando postman (Produtividade)

- [Set Bearer Token as Environment Variable in Postman for All APIs (Medium)](https://iroshandu.medium.com/set-bearer-token-as-environment-variable-in-postman-for-all-apis-13277e3ebd78)

---

## Plugin maven testes de integração

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

---

## Excelente exemplo integração de springboot + firebase

- [firebase-integration-spring-boot (GitHub)](https://github.com/hardikSinghBehl/firebase-integration-spring-boot/tree/main)

---

## Uso extensivo do RestClient

- [Spring Integration Rest Clients](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html)

---

## Utilizando Apache Commons Lang para comparação de valores

- [ComparableUtils (Apache Commons Lang)](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/compare/ComparableUtils.html)

---

## Instalação SonarQube via docker-compose com ajustes no pom.xml e instalação do sonar-type-nexus

- [SonarQube with Docker Compose: Complete Tutorial (Medium)](https://medium.com/@denis.verkhovsky/sonarqube-with-docker-compose-complete-tutorial-2aaa8d0771d4)
- [Install Sonatype Nexus 3 using Docker Compose (Medium)](https://medium.com/@chiemelaumeh1/install-sonatype-nexus-3-using-docker-compose-setup-nexus-repository-manager-for-node-js-project-47a3c5efe1ee)

---

## Github Actions

- [Let's create a Spring Boot app with MySQL, Docker & Docker Compose (Medium)](https://ilkerguldali.medium.com/1-4-lets-create-a-spring-boot-app-with-mysql-docker-docker-compose-8acaee3a2c4d)

---

## Boas práticas docker

- [Dockerizing Your Spring Boot Application (Medium)](https://medium.com/@bubu.tripathy/dockerizing-your-spring-boot-application-75bf2c6568d0)

---

## Continuar com a conexao insegura no chrome, devido erro ou handshake de certificado nao valido:

- sendCommand(SecurityInterstitialCommandId.CMD_PROCEED)
- Executar o comando acima no `console` no modo desenvolvedor do browser (F12)

---

## Configuração de Logging SpringBoot (Considerando Profile):

- [Spring Boot Logging (Codex)](https://medium.com/codex/spring-boot-logging-da61911ce8e6)
- [Spring Boot Logging (Baeldung)](https://www.baeldung.com/spring-boot-logging)

---

## Implementando leitura de variaveis do pom.xml dentro do `application.properties` ou `application.yaml`:

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
    - [Maven Resources Plugin (MVN Repository)](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-resources-plugin)
    - [Spring Boot Properties and Configuration (Spring Docs)](https://docs.spring.io/spring-boot/docs/1.4.x/reference/html/howto-properties-and-configuration.html)
- Abaixo exemplo de uso no `application.yaml`:

```yaml
  versao:
    mobile: @versao.mobile@
    api: @versao.api@
```

---

## Install `vim` on MacOS

- Define `vim` default editor in GIT
    - ```bash git config --global core.editor "vim" ```

---

## Arquitetura Hexagonal

- [Spring Hexagonal (Reflectoring)](https://reflectoring.io/spring-hexagonal/)
- [Hexagonal Architecture, DDD, and Spring (Baeldung)](https://www.baeldung.com/hexagonal-architecture-ddd-spring)

---

## Aventurando no GraalVM

- [Optimising Performance with GraalVM (Codex)](https://medium.com/codex/optimising-performance-with-graalvm-a-guide-to-migrating-a-spring-boot-project-to-native-image-fbb2dcf5d405)

---

## Pilares XP

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

---

## Pilares TDD

- [Mockito (Javadoc)](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/snapshot/user-guide/index.html#overview-getting-started-example-projects)
- [Spring Boot Test Auto-Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html)
- [Testes Unitários (Hora de Codar)](https://www.horadecodar.com.br/2021/07/23/como-desfazer-um-git-merge-no-repositorio-local/)

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
        ....
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

---

## Falhas de Serializacao e Deserializacao

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

---

## Como usar o LocalStack para emular serviços AWS localmente

LocalStack é uma ferramenta que permite simular diversos serviços da AWS (como S3, DynamoDB, SQS, SNS, etc.) em um ambiente local, sem precisar se conectar à nuvem. Isso facilita o desenvolvimento e testes de aplicações que dependem desses serviços, reduzindo custos e evitando configurações complexas em ambientes reais de nuvem.

---

### 1. Instalação

A forma mais simples de rodar o LocalStack é via Docker. Certifique-se de ter o Docker instalado e em execução na sua máquina.

#### 1.1 Via Docker Compose

Crie um arquivo chamado “docker-compose.yml” com o seguinte conteúdo de exemplo:

```yaml
version: "3.8"
services:
  localstack:
    image: localstack/localstack:latest
    container_name: localstack
    ports:
      - "4566:4566"   # Porta principal para todos os serviços em LocalStack
      - "4571:4571"   # Usada em algumas versões mais antigas para IAM, etc.
    environment:
      - SERVICES=s3,dynamodb,sqs,sns,lambda
      - DEBUG=1
      - DATA_DIR=/tmp/localstack/data
    volumes:
      - "./localstack-data:/tmp/localstack"
```

Em seguida, rode o comando:

```bash
docker-compose up -d
```

Isso vai baixar a imagem e iniciar o contêiner do LocalStack em segundo plano (em background).  

#### 1.2 Sem Docker Compose

Caso não queira usar Docker Compose, pode rodar diretamente:

```bash
docker run --rm -it -p 4566:4566 \
  -e SERVICES=s3,dynamodb,sqs,sns,lambda \
  localstack/localstack:latest
```

---

### 2. Configuração do AWS CLI

Para usar as ferramentas da AWS (CLI ou SDKs) apontando para LocalStack, basta definir as variáveis de endpoint e credenciais de teste (que serão ignoradas pelo LocalStack, mas ainda necessárias sintaticamente).

Exemplo de configuração usando AWS CLI:

```bash
aws configure
```

Preencha com qualquer valor, pois não será utilizado de fato:  
• AWS Access Key ID: test  
• AWS Secret Access Key: test  
• Default region name: us-east-1  
• Default output format: json  

Depois, para cada comando, inclua o parâmetro `--endpoint-url=http://localhost:4566`.  

Por exemplo, para criar um bucket S3 no LocalStack:

```bash
aws s3 mb s3://meu-bucket-teste \
  --endpoint-url=http://localhost:4566
```

---

### 3. Exemplos de Uso

Abaixo alguns exemplos de uso para S3 e DynamoDB, mas a lógica é semelhante para outros serviços.

#### 3.1 S3

1. Criar um bucket:
   ```bash
   aws s3 mb s3://meu-bucket-local \
     --endpoint-url=http://localhost:4566
   ```

2. Enviar um arquivo (upload) para o bucket:
   ```bash
   echo "Conteúdo de teste" > arquivo.txt
   aws s3 cp arquivo.txt s3://meu-bucket-local/arquivo.txt \
     --endpoint-url=http://localhost:4566
   ```

3. Listar objetos do bucket:
   ```bash
   aws s3 ls s3://meu-bucket-local \
     --endpoint-url=http://localhost:4566
   ```

#### 3.2 DynamoDB

1. Criar uma tabela:

   ```bash
   aws dynamodb create-table \
     --table-name MinhaTabela \
     --attribute-definitions AttributeName=Id,AttributeType=S \
     --key-schema AttributeName=Id,KeyType=HASH \
     --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
     --endpoint-url=http://localhost:4566 \
     --region us-east-1
   ```

2. Inserir item na tabela:
   ```bash
   aws dynamodb put-item \
     --table-name MinhaTabela \
     --item '{"Id": {"S": "001"}, "Nome": {"S": "Teste"}}' \
     --endpoint-url=http://localhost:4566 \
     --region us-east-1
   ```

3. Obter item da tabela:
   ```bash
   aws dynamodb get-item \
     --table-name MinhaTabela \
     --key '{"Id": {"S": "001"}}' \
     --endpoint-url=http://localhost:4566 \
     --region us-east-1
   ```

---

## 4. Integração com Aplicações Java (ou Outras Linguagens)

Se você tem uma aplicação Java (Spring Boot, Jakarta, etc.) que utiliza AWS SDK, basta configurar o endpoint para `http://localhost:4566`. Por exemplo, se estiver usando o SDK v2 da AWS para S3:


```java
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

public class S3LocalStackExample {
    public static void main(String[] args) {
        // Credenciais de teste
        AwsBasicCredentials credentials = AwsBasicCredentials.create("test", "test");

        // Cria client S3 apontando para o LocalStack
        S3Client s3Client = S3Client.builder()
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.US_EAST_1)
                .build();

        // Exemplo: cria bucket
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket("meu-bucket-local")
                .build();

        s3Client.createBucket(createBucketRequest);
    }
}
```

O mesmo princípio vale para DynamoDB, SQS, SNS, etc., trocando o client específico.

---

## 5. Dicas e Práticas Recomendadas

1. Verifique sempre a versão do LocalStack, pois nem todos os serviços ou recursos AWS são suportados 100%.  
2. Mantenha seu arquivo “docker-compose.yml” bem organizado, incluindo variáveis de ambiente para cada serviço que queira emular.  
3. Em testes automatizados, é comum iniciar o LocalStack antes dos testes e derrubá-lo logo depois, garantindo um ambiente limpo.  
4. Pode-se armazenar dados de forma persistente (mesmo depois de parar o contêiner) definindo um volume para o caminho “/tmp/localstack”.  
5. Caso seu cenário use Lambdas, confira se o LocalStack necessita de configurações adicionais (por exemplo, LocalStack Pro).  

---

## Conclusão

LocalStack é uma ferramenta extremamente útil para desenvolvimento e testes offline de microserviços ou aplicações que interagem com diversos serviços da AWS. Com apenas alguns ajustes na configuração do Docker e do AWS CLI (ou SDK), você consegue emular S3, DynamoDB, SQS e outros recursos sem precisar de uma conta ou ambiente AWS real.

---


# 🟦 Framework Dinâmico com Spring Data JPA e Specifications

- Mais dicas sobre o assunto
	- [Dynamic Query with Specification Interface in Spring Data JPA (Medium)](https://medium.com/@bubu.tripathy/dynamic-query-with-specification-interface-in-spring-data-jpa-ae8764e32162#:~:text=The%20Specification%20interface%20in%20Spring%20Data%20JPA%20is%20a%20powerful)

Este documento reúne duas ideias principais para construção de consultas dinâmicas usando Spring Data JPA e Specifications:  
1. A geração de Specifications de forma genérica e refletida, para filtrar por qualquer campo.  
2. A possibilidade de escolher operadores de comparação (EQUAL, LIKE, GREATER_THAN etc.), tornando a busca ainda mais flexível.

--------------------------------------------------------------------------------

## Sumário

1. [Visão Geral](#visão-geral)  
2. [Enum de Operadores](#enum-de-operadores)  
3. [Objeto de Filtro](#objeto-de-filtro)  
4. [Builder de Specifications Dinâmicas](#builder-de-specifications-dinâmicas)  
   4.1 [Lógica de Criação de Predicates](#lógica-de-criação-de-predicates)  
   4.2 [Conversão de Tipos (CastValue)](#conversão-de-tipos-castvalue)  
5. [Exemplo de Uso (Repository, Service)](#exemplo-de-uso-repository-service)  
   5.1 [Entidade de Exemplo](#entidade-de-exemplo)  
   5.2 [Repositório com JpaSpecificationExecutor](#repositório-com-jpaspecificationexecutor)  
   5.3 [Service/Controller Exemplo](#servicecontroller-exemplo)  
6. [Observações Finais](#observações-finais)

--------------------------------------------------------------------------------

## 1. Visão Geral

A abordagem aqui descrita combina:
- Reflection para identificar se o campo informado para filtro realmente existe na entidade.  
- Classe auxiliar (DynamicSpecificationBuilder<T>) que monta, de forma dinâmica, as cláusulas WHERE com base em uma lista de filtros.  
- Possibilidade de aplicar JOIN de forma flexível (escolhendo por qual atributo será feito e qual JoinType).  
- Operadores de comparação para cada filtro, como EQUAL, LIKE, GREATER_THAN, etc.  

Com isso, você consegue construir queries complexas sem precisar criar vários métodos de repositório manualmente.

--------------------------------------------------------------------------------

## 2. Enum de Operadores

Esta enum define os operadores que podem ser usados ao filtrar campos:

```java
package com.exemplo.framework;

public enum FilterOperator {
    EQUAL,
    LIKE,
    GREATER_THAN,
    GREATER_OR_EQUAL,
    LESS_THAN,
    LESS_OR_EQUAL
}
```

- EQUAL → campo = valor  
- LIKE → campo LIKE %valor% (específico para Strings, em geral)  
- GREATER_THAN → campo > valor  
- GREATER_OR_EQUAL → campo >= valor  
- LESS_THAN → campo < valor  
- LESS_OR_EQUAL → campo <= valor  

--------------------------------------------------------------------------------

## 3. Objeto de Filtro

Representa cada filtro individual. Cada filtro possui:  
1. Nome do campo (fieldName).  
2. Operador (FilterOperator).  
3. Valor (value).

```java
```java
package com.exemplo.framework;

public class Filter {
    private String fieldName;
    private FilterOperator operator;
    private Object value;

    public Filter(String fieldName, FilterOperator operator, Object value) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }
}
```

--------------------------------------------------------------------------------

## 4. Builder de Specifications Dinâmicas

A seguir está um exemplo de implementação de `DynamicSpecificationBuilder<T>`, que:  
1. Recebe uma lista de objetos Filter (cada um com campo, operador e valor).  
2. Recebe também, opcionalmente, um Map para joins, indicando qual atributo deve ser “joined” e que tipo de join (`JoinType.LEFT`, `JoinType.INNER`, etc.).  
3. Cria e retorna um objeto `Specification<T>` pronto para ser usado com repositórios que implementem `JpaSpecificationExecutor<T>`.

```java
package com.exemplo.framework;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DynamicSpecificationBuilder<T> {

    public Specification<T> buildSpecification(
            List<Filter> filters,
            Map<String, JoinType> joinFields
    ) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            // Define joins dinamicamente, se necessário
            if (joinFields != null) {
                joinFields.forEach((joinField, joinType) -> {
                    root.join(joinField, joinType);
                });
            }

            List<Predicate> predicates = new ArrayList<>();

            // Construção dinâmica dos Predicates
            if (filters != null) {
                for (Filter filtro : filters) {
                    if (Objects.nonNull(filtro.getValue())) {
                        try {
                            String fieldName = filtro.getFieldName();
                            FilterOperator operator = filtro.getOperator();
                            Object value = filtro.getValue();

                            // Verificar se o campo existe na entidade (Reflection)
                            Field field = root.getJavaType().getDeclaredField(fieldName);
                            Class<?> fieldType = field.getType();

                            // Cria o Predicate conforme o operador especificado
                            Predicate predicate = createPredicate(
                                    cb,
                                    root,
                                    fieldName,
                                    fieldType,
                                    operator,
                                    value
                            );

                            if (predicate != null) {
                                predicates.add(predicate);
                            }

                        } catch (NoSuchFieldException e) {
                            // Caso o campo não exista, você pode ignorar ou registrar log
                        }
                    }
                }
            }

            // Combina todos os Predicates com AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Cria o Predicate de acordo com o operador solicitado.
     */
    private Predicate createPredicate(
            CriteriaBuilder cb,
            Root<T> root,
            String fieldName,
            Class<?> fieldType,
            FilterOperator operator,
            Object value
    ) {
        switch (operator) {
            case EQUAL:
                // Igualdade
                return cb.equal(root.get(fieldName), castValue(fieldType, value));

            case LIKE:
                // LIKE geralmente para Strings
                if (fieldType.equals(String.class)) {
                    String pattern = "%" + value.toString().toLowerCase() + "%";
                    return cb.like(cb.lower(root.get(fieldName)), pattern);
                } else {
                    // Fallback para igual caso não seja String
                    return cb.equal(root.get(fieldName), castValue(fieldType, value));
                }

            case GREATER_THAN:
                // Ajuste para Number (ou datas, se precisar)
                if (Number.class.isAssignableFrom(fieldType)) {
                    return cb.gt(root.get(fieldName), (Number) castValue(fieldType, value));
                }
                break;

            case GREATER_OR_EQUAL:
                if (Number.class.isAssignableFrom(fieldType)) {
                    return cb.ge(root.get(fieldName), (Number) castValue(fieldType, value));
                }
                break;

            case LESS_THAN:
                if (Number.class.isAssignableFrom(fieldType)) {
                    return cb.lt(root.get(fieldName), (Number) castValue(fieldType, value));
                }
                break;

            case LESS_OR_EQUAL:
                if (Number.class.isAssignableFrom(fieldType)) {
                    return cb.le(root.get(fieldName), (Number) castValue(fieldType, value));
                }
                break;
        }

        // Não encontrou operador adequado? Pode retornar null ou um predicate "sempre verdadeiro"
        return null;
    }

    /**
     * Converte value para o tipo do campo (fieldType).
     * Pode ser expandido para datas, enums, etc.
     */
    private Object castValue(Class<?> fieldType, Object value) {
        if (value == null) return null;

        if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
            return Long.parseLong(value.toString());
        }
        if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
            return Integer.parseInt(value.toString());
        }
        if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
            return Double.parseDouble(value.toString());
        }
        if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
            return Float.parseFloat(value.toString());
        }

        // Outras conversões especiais podem entrar aqui (por exemplo, datas, enums).
        return value;
    }
}
```

### 4.1. Lógica de Criação de Predicates

- EQUAL → `cb.equal`  
- LIKE → se for campo String, usa `cb.like` com “%valor%”; caso contrário, faz fallback para EQUAL.  
- GREATER_THAN, GREATER_OR_EQUAL, LESS_THAN, LESS_OR_EQUAL → Exemplos de uso com Number. Pode-se expandir para outros tipos (por exemplo, datas).  

### 4.2. Conversão de Tipos (castValue)

- Exemplo simples para números primitivos (long, int, etc.).  
- Se precisar converter para datas (LocalDate, Date), enums, ou tipos complexos, basta adicionar mais lógica.

--------------------------------------------------------------------------------

## 5. Exemplo de Uso (Repository, Service)

### 5.1. Entidade de Exemplo

```java
package com.exemplo.dominio;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class MinhaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    // Exemplo de relacionamento
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}

```

### 5.2. Repositório com JpaSpecificationExecutor


```java
package com.exemplo.repositorio;

import com.exemplo.dominio.MinhaEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MinhaEntidadeRepository 
    extends JpaRepository<MinhaEntidade, Long>, JpaSpecificationExecutor<MinhaEntidade> {
    
    // Métodos adicionais, se necessário
}
```

### 5.3. Service/Controller Exemplo

Veja como usar o builder para criar Specifications corretamente:

```java
package com.exemplo.servico;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exemplo.dominio.MinhaEntidade;
import com.exemplo.framework.DynamicSpecificationBuilder;
import com.exemplo.framework.Filter;
import com.exemplo.framework.FilterOperator;
import com.exemplo.repositorio.MinhaEntidadeRepository;

@Service
public class MinhaEntidadeService {

    private final MinhaEntidadeRepository repo;

    public MinhaEntidadeService(MinhaEntidadeRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<MinhaEntidade> buscarComFiltros() {
        // Exemplos de filtros
        List<Filter> filtros = new ArrayList<>();
        // LIKE em "titulo" que contenha "abc"
        filtros.add(new Filter("titulo", FilterOperator.LIKE, "abc"));
        // id > 5
        filtros.add(new Filter("id", FilterOperator.GREATER_THAN, 5));

        // Exemplos de joins
        Map<String, JoinType> joins = new HashMap<>();
        joins.put("categoria", JoinType.LEFT);

        // Constrói specification
        DynamicSpecificationBuilder<MinhaEntidade> builder = new DynamicSpecificationBuilder<>();
        var spec = builder.buildSpecification(filtros, joins);

        // Executa a busca
        return repo.findAll(spec);
    }
}
```

--------------------------------------------------------------------------------

## 6. Observações Finais

1. Este “framework” de Specifications baseadas em Reflection permite grande flexibilidade, mas deve ser usado com cautela em ambientes externos, pois a possibilidade de “filtrar qualquer campo” pode expor dados ou gerar queries muito pesadas.  
2. Restrinja e valide campos e operadores permitidos, conforme a necessidade.  
3. Para suporte avançado a datas, enums e outros tipos, expanda o método `castValue`.  
4. Para adicionar operadores como NOT EQUAL, BETWEEN, IN etc., basta ampliar a enum e a lógica de criação de `Predicate`.  
5. Sempre verifique se as colunas/relacionamentos passados como filtros realmente existem na entidade. Caso não existam, decida se deseja ignorar o filtro ou lançar um erro.

---

- Isso conclui um modelo de “framework” dinâmico de Specifications com Spring Data, incluindo operadores relacionais/comparativos e suporte a JOIN. Sinta-se livre para adaptar as classes, métodos e a estrutura do projeto de acordo com suas necessidades.  


# 🐪 Documentação Apache Camel

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


# api-vendas

>[!IMPORTANT]
> Índice
- [Guia explicativo de como executar o projeto localmente](#guia-explicativo-de-como-executar-o-projeto-localmente)
- [Guia explicativo de como dockerizar o projeto em seguinda executá-lo localmente sem o docker-compose](#guia-explicativo-de-como-dockerizar-o-projeto-em-seguinda-executa-lo-localmente-sem-o-docker-compose)
- [Guia explicando como dockerizar o projeto em seguinda executá-lo localmente com o docker-compose](#guia-explicando-como-dockerizar-o-projeto-em-seguinda-executa-lo-localmente-com-o-docker-compose)
- [Referências](#referências)
- [Spring com Redis](#spring-com-redis)
- [Configurando Banner do Spring](#configurando-banner-do-spring)


> Este projeto aborda os seguintes tópicos:
    - Requisitos (MVP)
        - Cadastro de Clientes
        - Cadastro de Produtos
        - Registro de Pedidos
            - Aprovação de Pedidos
            - Cancelamento de Pedidos
    - Tecnologias Utilizadas
        - Java 17
        - Spring Boot
        - PostgreSQL
        - H2 Database
        - Lombok
        - JPA/Hibernate
        - JdbcTemplate
        - Docker
        - Git
    - Abordagens/Boas Práticas
        - DTO Pattern + Record Pattern (new feature do Java 17)
        - Domain Driven Design
        - Testes Unitários
        - Clean Code
        - Paginação de Resultados com Spring Data JPA
        - HATEOAS
        - Swagger
        - Banner do Spring
        - Tratamento de Erros
        - Segurança
        - Cache com Redis
        - Versionamento de API
        - Documentação de API com Swagger
        - Uso do @JsonManagedReference e @JsonBackReference para evitar recursividade infinita em relacionamentos bidirecionais



#   Guia explicativo de como executar o projeto localmente


> [!WARNING]
Certifique-se de que o Java 17 esteja instalado e configurado na sua máquina local, apache-maven, git, docker e etc. Por padrão, o projeto está configurado para executar o perfil de desenvolvimento, ou seja, o banco de dados H2 será utilizado.


### Abra o terminal e execute o comando abaixo para clonar o projeto:
```bash
git clone git@github.com:sfidencio/my-study-projects.git
```
### Acessando o diretório raiz do projeto:
```
cd api-vendas
```


#### Limpando e instalando dependências, bem como o artefato final, ou seja, o .jar:
```bash
mvn clean install
```


#### Executando o projeto:
```bash
mvn spring-boot:run
```


> [!IMPORTANT]
> Caso tenha executado com sucesso a aplicação, deverá aparecer a seguinte mensagem -> Started Application in 2.11 seconds (process running for 2.3)


### Realizando teste de cadastro de cliente via curl:
```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/salvar --data '{"nome":"Fulano","cpf":"41909644099", "email":"fulano@gmail.com" }' -H "Content-Type: application/json"  -X POST
```


### Consultando cliente via curl:
```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/consulta/1 -H "Content-Type: application/json"  -X GET
```


> [!IMPORTANT]
> Pronto, agora abra o projeto no IntelliJ IDEA e divirta-se!


#   Guia explicativo de como dockerizar o projeto em seguinda executa-lo localmente sem o docker-compose


### Criando arquivo Dockerfile e o arquivo docker-compose.yml


> [!IMPORTANT]
> Executar sempre um `mvn clean install` antes de criar a imagem customizada.




>Dockerfile


```
FROM openjdk:17-alpine3.13
LABEL authors="sebastiaofidencio"


RUN apk add --no-cache bash \
   && apk add --no-cache curl \
   && apk add --no-cache iputils




WORKDIR /app


#Abaixo podemos alterar o profile de production para development
CMD ["java","-Dspring.profiles.active=development", "-jar", "app.jar"]


CMD ["java", "-jar", "app.jar"]
```


### Acesse o diretório raiz do projeto(root), via terminal, e execute o comando abaixo para criar a imagem customizada
>[!WARNING]
> Onde está `sfidencio` deverá ser substituído pelo seu usuário do dockerhub, e pressupondo que você já tenha uma conta no docker hub e tenha feito login previamente.
> Caso não tenha feito login o comando e `docker login` e informe seu usuário e senha.


```bash
docker build --platform linux/amd64 -t sfidencio/api-vendas:latest .
```

### Executando imagem customizada em primeiro plano ou "attached" no terminal "-it", visto que a opcao "-rm" remove o container ao finalizar

```bash
docker run --rm -it --name myapp -p 8080:8080 sfidencio/api-vendas:latest
```


### Executando imagem customizada em background "-d", visto que a opcao "-rm" remove o container ao finalizar

```bash
docker run --rm -d --name myapp -p 8080:8080 sfidencio/api-vendas:latest
```

>[!TIP]
> Caso seja necessario verificar os logs do container, execute o comando abaixo:
```bash
docker logs myapp
```

>[!TIP]
> Caso seja necessario parar o container, execute o comando abaixo:
```bash
docker stop myapp
```

### Acessando aplicação - Cadastrando Cliente


```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/salvar --data '{"nome":"Fulano","cpf":"41909644099", "email":"fulano@gmail.com" }' -H "Content-Type: application/json"  -X POST
```


### Acessando aplicação - Consultado cliente com id-> 1


```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/consulta/1 -H "Content-Type: application/json"  -X GET
```

### Acessando aplicação - Listando todos Clientes Cadastrados


```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/consulta-todos-clientes -H "Content-Type: application/json"  -X GET
```

### Acessando container caso seja necessário

```bash
docker exec -it myapp sh
```

# Guia explicando como dockerizar o projeto em seguinda executa-lo localmente com o docker-compose
> [!IMPORTANT]
> Executar sempre um `mvn clean install`, pois o docker-compose irá criar a imagem customizada e subir o container, e se não tivermos o .jar, o container não sobe.

>[!IMPORTANT]
> Nesse cenario, o docker-compose.yml deverá estar na raiz do projeto, ou seja, no mesmo diretório onde está o Dockerfile.

>[!IMPORTANT]
> Vamos subir o banco de dados postgres e a aplicação em containers, isso implica que teremos que mudar o profile de `development` para `production`, pois a aplicação não estará mais usando o banco de dados H2, mas sim o postgres. Altere o Dockerfile na linha `-Dspring.profiles.active=development` para `-Dspring.profiles.active=production`.

>[!TIP]
> Observe que estamos usando variaveis de ambiente no arquivo docker-compose.yml, ou seja, as variaveis de ambiente são passadas para a aplicação via docker-compose.yml, e a aplicação as recebe via System.getenv("NOME_DA_VARIAVEL_DE_AMBIENTE") ou @Value("${NOME_DA_VARIAVEL_DE_AMBIENTE}") ou dentro do arquivo application.yaml ou application.properties no seguinte formato(Exemplo apenas): `spring.datasource.url=${SPRING_DATASOURCE_URL}`.

>docker-compose.yml

```
version: '3.7'
services:
 db:
   image: postgres:13-alpine
   container_name: postgres
   restart: always
   environment:
     POSTGRES_USER: postgres
     POSTGRES_PASSWORD: postgres@@
     POSTGRES_DB: myapp
   ports:
       - "5432:5432"
   volumes:
       - postgres-data:/var/lib/postgresql/data
 app:
   image: myapp:latest
   platform: linux/amd64
   build: .
   ports:
       - "8080:8080"
   depends_on:
     - db
   environment:
       SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/myapp
       SPRING_DATASOURCE_USERNAME: postgres
       SPRING_DATASOURCE_PASSWORD: postgres@@
       SPRING_JPA_HIBERNATE_DDL_AUTO: update


networks:
 myapp:
   driver: bridge


volumes:
   postgres-data:
     driver: local
```

>[!TIP]
> Observe bem a estrutura do arquivo docker-compose.yml, pois é muito importante para o funcionamento correto do projeto.


>[!TIP]
> O arquivo docker-compose.yml é composto por 3 seções, sendo elas: services, networks e volumes.


>[!TIP]
> A seção services é composta por 2 serviços, sendo eles: db e app.


>[!TIP]
> A seção networks é composta por 1 rede, sendo ela: myapp. A aplicação e o banco de dados estarão na mesma rede.


>[!TIP]
> O parâmetro `depends_on` é muito importante, pois ele garante que o banco de dados esteja no ar antes da aplicação subir.


>[!TIP]
> A seção volumes é composta por 1 volume, sendo ele: `postgres-data`. Esse volume é responsável por persistir os dados do banco de dados postgres, pois o container é efêmero(imutável), ou seja, se o container for derrubado, os dados serão perdidos, logo a gravação dos dados é feita no volume, que esta "fora" do container.

>[!TIP]
> O volume é criado automaticamente pelo docker-compose, caso não exista. 


### Subindo aplicação no docker localmente usando docker-compose aliado ao Dockerfile, em background "-d"


```bash
sudo docker-compose up --build -d
```



>[!WARNING]
>Repita os testes de cadastro de clientes, consulta e listagem via curl, conforme descrito acima, pois o banco de dados H2 não será mais utilizado, mas sim o postgres.

### Parando containers


```bash
sudo docker-compose down
```

# Spring com Redis
## Tutorial basico de como usar o Redis via CLI
> [!IMPORTANT]
> Para executar o redis standalone via docker, execute o comando abaixo:
```bash
docker run --rm -d --name redis -p 6379:6379 redis
```
>Acesse o container do Redis
```bash
docker exec -it redis bash
```
>Execute o comando abaixo para acessar o Redis CLI (Dentro do Container)
```bash
redis-cli
```

>[!WARNING]
> Todos comandos abaixo devem ser executados dentro do redis-cli.
>Teste Redis
```bash
ping
```

>Visualizar todas as chaves
```bash
KEYS *
```
>Visualizar valor da chave
```bash
GET <chave>
```
>Incluir chave e valor
```bash
SET <chave> <valor>
```
>Excluir chave
```bash
DEL <chave>
```
>Caso queira limpar o Redis, execute o comando abaixo:
```bash
FLUSHALL
```
>[!WARNING]
> Ate aqui, usamos do redis-cli.


## Tutorial basico de como usar o Redis via Spring
> [!IMPORTANT]
> Na classe Application.java, temos um exemplo, via `CommandLineRunner`, de como realizar operacoes basicas no Redis, utilizando a classe Helper, `RedisTemplate`.


### Configurando Banner do Spring
> [!TIP]
> Para configurar banner do spring, acesse o link abaixo:
> https://devops.datenkollektiv.de/banner.txt/index.html
> Basta, copiar o banner e colar no arquivo application.yaml ou application.properties, conforme exemplo abaixo:
> application.yaml
```
spring:
  application:
    name: api-vendas
  banner:
    charset: UTF-8
    location: classpath:banner.txt
```

>[!IMPORTANT]
>Referências utilizadas em todo o projeto:

>Tutoriais e cursos gratuitos: 
>+ https://tpbabparn.medium.com/spring-boot-3-1-integrated-docker-compose-on-development-environment-spring-webflux-example-1ddcbfe052f
>+ https://springhow.com/spring-boot-and-postgres-using-docker-compose/
>+ https://www.baeldung.com/ops/docker-compose-links-depends-on
>+ https://www.baeldung.com/spring-boot-docker-start-with-profile
>+ https://blog.devgenius.io/how-to-handle-constraint-violation-exception-using-controlleradvice-in-spring-boot-2f61147d19de
>+ https://reflectoring.io/bean-validation-with-spring-boot/
>+ https://www.javadevjournal.com/spring-boot/spring-custom-validation-message-source/
>+ https://www.baeldung.com/spring-valid-vs-validated
>+ https://salithachathuranga94.medium.com/validation-and-exception-handling-in-spring-boot-51597b580ffd
>+ https://www.udemy.com/user/dougllas-sousa/
>+ https://pt.stackoverflow.com/questions/207188/depend%C3%AAncia-circular-em-api-rest-com-spring-boot
>+ https://medium.com/@seonggil/creating-a-maturity-level-3-rest-api-with-hateoas-fcd76d1b2db9
>+ https://www.baeldung.com/spring-custom-validation-message-source
>+ https://reflectoring.io/bean-validation-with-spring-boot/
>+ https://medium.com/yildiztech/decoding-i18n-challenges-in-spring-boot-3-exploring-internationalization-895a4ac627df
>+ https://medium.com/@seonggil/creating-a-maturity-level-3-rest-api-with-hateoas-fcd76d1b2db9 (**Implementing HATEOAS**)
>+ https://www.baeldung.com/spring-data-jpa-pagination-sorting
>+ https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
>+ https://spring.io/guides/gs/accessing-data-jpa/
>+ https://spring.io
>+ https://hibernate.org
>+ https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/record.html
 
>Referências utilizadas para implementar o cache com Redis:
>+ https://www.baeldung.com/spring-boot-redis-cache
>+ https://www.digitalocean.com/community/tutorials/spring-boot-redis-cache
>+ https://www.bezkoder.com/spring-boot-redis-cache-example/
>+ https://redis.io/docs/connect/cli/
>+ https://premika-17.medium.com/implementing-redis-in-spring-boot-3d2756e5ab69
>+ https://medium.com/javarevisited/classcast-exception-when-using-redis-and-springboot-frameworks-in-conjunction-ea132dd0d7ea
>+ https://stackoverflow.com/questions/74557099/how-to-delete-all-data-from-redis-using-redistemplate-in-java
>+ https://www.baeldung.com/spring-boot-evict-cache (Limpando o cache de várias formas)
>+ https://medium.com/@aedemirsen/cache-structure-in-spring-boot-projects-with-redis-2c5751bca9eb

>Guia sobre utilização do Banner do Spring:
>+ https://devops.datenkollektiv.de/banner.txt/index.html

>Guia sobre configuração do redis no docker:
>+ https://cloudinfrastructureservices.co.uk/run-redis-with-docker-compose/ (Configuração mais completa)
>+ https://medium.com/nerd-for-tech/setting-up-a-standalone-redis-instance-2721a7318037
>+ https://zomro.com/blog/faq/301-kak-ustanovit-redis-v-docker
>+ https://devopscell.com/docker/docker-compose/volumes/2018/01/16/volumes-in-docker-compose.html 

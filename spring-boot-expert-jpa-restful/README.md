
# spring-boot-expert-jpa-restful


>[!IMPORTANT]
> Índice
- [Guia explicativo de como executar o projeto localmente](#guia-explicativo-de-como-executar-o-projeto-localmente)
- [Guia explicativo de como dockerizar o projeto em seguinda executa-lo localmente sem o docker-compose](#guia-explicativo-de-como-dockerizar-o-projeto-em-seguinda-executa-lo-localmente-sem-o-docker-compose)
- [Guia explicando como dockerizar o projeto em seguinda executa-lo localmente com o docker-compose](#guia-explicando-como-dockerizar-o-projeto-em-seguinda-executa-lo-localmente-com-o-docker-compose)
- [Referências](#referências)


> Este projeto aborda os seguintes tópicos:
- [x] [spring-boot-expert-jpa-restful]
    - API Restful de Vendas
        - Cadastro de Clientes
        - Cadastro de Produtos
        - Registro de Pedidos
            - Aprovação de Pedidos
            - Cancelamento de Pedidos
    - Tecnologias Utilizadas
        - Java 17
            - Record Pattern (new feature)
        - Spring Boot
        - PostgreSQL
        - H2 Database
        - Lombok
        - JPA/Hibernate
        - JdbcTemplate
        - Docker
        - Git
    - Abordagens/Boas Práticas
        - DTO Pattern
        - Domain Driven Design
        - Testes Unitários
        - Clean Code
        - Paginação de Resultados com Spring Data JPA




#   Guia explicativo de como executar o projeto localmente


> [!WARNING]
Certifique-se de que o Java 17 esteja instalado e configurado na sua máquina local, apache-maven, git, docker e etc. Por padrão, o projeto está configurado para executar o perfil de desenvolvimento, ou seja, o banco de dados H2 será utilizado.


### Abra o terminal e execute o comando abaixo para clonar o projeto:
```bash
git clone git@github.com:sfidencio/my-study-projects.git
```
### Acessando o diretório raiz do projeto:
```
cd spring-boot-expert-jpa-restful
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
curl -kvs http://localhost:8080/base/v1/api/clientes/salvar --data '[{"nome":"Fulano","cpf":"41909644099", "email":"fulano@gmail.com" },{"nome":"Ciclano","cpf":"41909644099", "email":"ciclano@gmail.com" }]' -H "Content-Type: application/json"  -X POST
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
docker build --platform linux/amd64 -t sfidencio/spring-boot-expert-jpa-restful:latest .
```

### Executando imagem customizada em primeiro plano ou "attached" no terminal "-it", visto que a opcao "-rm" remove o container ao finalizar

```bash
docker run --rm -it --name myapp -p 8080:8080 sfidencio/spring-boot-expert-jpa-restful:latest
```


### Executando imagem customizada em background "-d", visto que a opcao "-rm" remove o container ao finalizar

```bash
docker run --rm -d --name myapp -p 8080:8080 sfidencio/spring-boot-expert-jpa-restful:latest
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
curl -kvs http://localhost:8080/base/v1/api/clientes/salvar --data '[{"nome":"Fulano","cpf":"41909644099", "email":"fulano@gmail.com" },{"nome":"Ciclano","cpf":"41909644099", "email":"ciclano@gmail.com" }]' -H "Content-Type: application/json"  -X POST
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
>[!IMPORTANT]
> Nesse cenario, o docker-compose.yml deverá estar na raiz do projeto, ou seja, no mesmo diretório onde está o Dockerfile.

>[!IMPORTANT]
> Vamos subir o banco de dados postgres e a aplicação em containers, isso implica que teremos que mudar o profile de `development` para `production`, pois a aplicação não estará mais usando o banco de dados H2, mas sim o postgres. Altere o Dockerfile na linha `-Dspring.profiles.active=development` para `-Dspring.profiles.active=production`.


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


### Parando containers


```bash
sudo docker-compose down
```

>[!IMPORTANT]
>Referências:
>+ https://tpbabparn.medium.com/spring-boot-3-1-integrated-docker-compose-on-development-environment-spring-webflux-example-1ddcbfe052f
>+ https://springhow.com/spring-boot-and-postgres-using-docker-compose/
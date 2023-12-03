# spring-boot-expert-jpa-restful

>[!IMPORTANT]
> Indice
- [Guia explicativo como executar o projeto localmente](#guia-explicativo-como-executar-o-projeto-localmente)
- 

>[!IMPORTANT]
> Este projeto aborta os seguintes tópicos:
- [x] [spring-boot-expert-jpa-restful]
    - API Restful de Vendas
        - Cadastro de Clientes
        - Cadastro de Produtos
        - Registro de Pedidos
            - Aprovacao de Pedidos
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
    - Abordagens/Boas Praticas
        - DTO Pattern
        - Domain Driven Design
        - Testes Unitários
        - Clean Code
        - Paginação de Resultados com Spring Data JPA


#   Guia explicativo como executar o projeto localmente 

> [!WARNING]
Certifique-se de que o Java 17 esteja instalado e configurado na sua máquina local, apache-maven, git, docker e etc. Por padrão, o projeto está configurado para executar o perfil de desenvolvimento, ou seja, o banco de dados H2 será utilizado.

Abra o terminal e execute o comando abaixo para clonar o projeto:
```bash
#git clone git@github.com:sfidencio/my-study-projects.git
```
Acessando o diretorio raiz do projeto:
```
#cd spring-boot-expert-jpa-restful
```

Limpando e instalando dependencias, bem como o artefato final, ou seja, o .jar:
```bash
#mvn clean install
```

Executando o projeto:
```bash
#mvn spring-boot:run
```

> [!IMPORTANT]
> Caso tenha executado com sucesso a aplicacao, devera aparecer a seguinte mensagem -> Started Application in 2.11 seconds (process running for 2.3)

#   Guia explicando como executar o projeto localmente via Docker

### 1. Criando arquivo Dockerfile e o arquivo docker-compose.yml

> [!IMPORTANT] 
> Observacoes: executar sempre um mvn clean install antes de criar a imagem customizada seja via Dockerfile ou docker-compose.yml


>Dockerfile

```
FROM openjdk:17-alpine3.13
LABEL authors="sebastiaofidencio"

RUN apk add --no-cache bash \
    && apk add --no-cache curl \
    && apk add --no-cache iputils 


WORKDIR /app

#Abaixo podemos alterar o profile de production para development
CMD ["java","-Dspring.profiles.active=production", "-jar", "app.jar"]

CMD ["java", "-jar", "app.jar"]
```

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


### 2. Removendo imagens existentes

```bash
docker rmi -f $(docker images -q)
```

### 3. Removendo containers existentes

```bash
docker rm -f $(docker ps -a -q)
```

### 4. Criando imagem customizada 

```bash
docker build --platform linux/amd64 -t sfidencio/spring-boot-expert-jpa-restful:latest .
```

### 5. Executando imagem customizada attachado o interpretador sh no terminal (entrando no tty), visto que a opcao "-rm" remove o conteiner ao finalizar

```bash
docker run --rm -it --name myapp -p 8080:8080 sfidencio/spring-boot-expert-jpa-restful:latest sh
```

### 6. Executando imagem customizada em background, visto que a opcao "-rm" remove o conteiner ao finalizar

```bash
docker run --rm -d --name myapp -p 8080:8080 sfidencio/spring-boot-expert-jpa-restful:latest
```
### 7. Acessando aplicacao - Cadastrando Cliente

```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/salvar --data '[{"nome":"Fulano","cpf":"41909644099", "email":"fulano@gmail.com" },{"nome":"Ciclano","cpf":"41909644099", "email":"ciclano@gmail.com" }]' -H "Content-Type: application/json"  -X POST
```

### 8. Acessando aplicacao - Consultado cliente com id-> 1

```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/consulta/1 -H "Content-Type: application/json"  -X GET
```

### 9. Acessando conteiner

```bash
docker exec -it myapp sh
```

### 10. Executando conteiner attachado("-it") ao terminal, visto que a opcao "-rm" remove o conteiner ao finalizar

```bash
docker run --rm -it --name myapp -p 8080:8080 sfidencio/spring-boot-expert-jpa-restful:latest
```

### 11. Acessando aplicacao - Listando todos Clientes Cadastrados

```bash
curl -kvs http://localhost:8080/base/v1/api/clientes/consulta-todos-clientes -H "Content-Type: application/json"  -X GET
```

### 12. Subindo aplicacao no docker localmente usando docker-compose aliado com Dockerfile, em background ("-d")

```bash
sudo docker-compose up --build -d
```

### 13. Parando conteiners 

```bash
sudo docker-compose down
```

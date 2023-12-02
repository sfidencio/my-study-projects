##   Subindo aplicacao no docker localmente usando imagem customizada via Dockerfile

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
  postgres:
    image: postgres:13-alpine
    container_name: postgres
    restart: unless-stopped
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres@@
      POSTGRES_DB: myapp
    ports:
        - "5432:5432"
    volumes:
        - postgres-data:/var/lib/postgresql/data
  app:
    image: myapp
    platform: linux/amd64
    build: .
    ports:
        - "8080:8080"
    depends_on:
      - postgres
    environment:
        SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/myapp
        SPRING_DATASOURCE_USERNAME: postgres
        SPRING_DATASOURCE_PASSWORD: postgres@@
        SPRING_JPA_HIBERNATE_DDL_AUTO: update
volumes:
    postgres-data:
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

### 5. Executando imagem customizada attachado o sh (entrando no tty), visto que a opcao "-rm" remove o conteiner ao finalizar

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

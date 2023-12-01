##   Subindo aplicacao no docker localmente usando imagem customizada via Dockerfile

### 1. Criando arquivo Dockerfile

```
FROM openjdk:17-alpine3.13
LABEL authors="sebastiaofidencio"

WORKDIR /app

COPY target/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
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
curl -kvs http://localhost:8080/base/v1/api/clientes/salvar --data '[{"nome":"Fulano","cpf":"41909644099", "email":"fulano@gmail.com" }]' -H "Content-Type: application/json"  -X POST
```

### 8. Acessando aplicacao - Listando Clientes

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


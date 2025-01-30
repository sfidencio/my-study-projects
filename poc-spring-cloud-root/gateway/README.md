# Projeto do Gateway

- Este projeto é responsável por intermediar as requisições entre o frontend e o backend.

# Configuração do Spring Cloud Gateway

O **Spring Cloud Gateway** é uma solução altamente eficaz para roteamento de solicitações, proporcionando uma maneira simplificada de integrar microserviços, manipular requisições, aplicar filtros e configurar segurança.

## 1. Adicionar Dependência do Spring Cloud Gateway

Primeiro, adicione a dependência do Spring Cloud Gateway no seu `pom.xml`:

```xml  
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

# Explicações básicas sobre o arquivo application.yaml em um gateway

- O arquivo application.yaml é responsável por configurar o gateway, nele é possível configurar o redirecionamento de requisições, o timeout, o path e o host.
- Abaixo segue um exemplo de configuração do arquivo application.yaml:

```yaml
spring:  
  cloud:  
    gateway:  
      routes:  
        - id: service1  
          uri: lb://SERVICE1  # Nome do serviço registrado no Eureka  
          predicates:  
            - Path=/service1/**  # Rota que o Gateway irá escutar  
          filters:  
            - StripPrefix=1  # Remove o prefixo da URL antes de encaminhar  

        - id: service2  
          uri: lb://SERVICE2  
          predicates:  
            - Path=/service2/**  
          filters:  
            - AddRequestHeader=X-Request-Foo, Bar  # Adiciona um cabeçalho à requisição  

server:  
  port: 8080  # A porta em que o Gateway irá ouvir
```
- Componentes Importantes na Configuração: 
  - Routes (Rotas)
     - id: Um identificador único para a rota.
     - uri: A URI do serviço de destino. Se você usar o Eureka, pode começar com lb:// seguido do nome do serviço.
     - predicates: Condições que determinam quando a rota deve ser usada. Exemplo: Path=/service1/** significa que todas as requisições que começam com /service1 serão encaminhadas para o serviço correspondente.
     - filters: Filtros aplicados às requisições antes que elas sejam enviadas para o serviço de destino. Alguns filtros comuns são:
     - StripPrefix: Remove um prefixo da URL antes de encaminhar.
     - AddRequestHeader: Adiciona cabeçalhos à requisição.
     - RewritePath: Altera o caminho da requisição.
     - RequestRateLimiter: Limita a taxa de requisições. 
  - Predicates (Predicados)
     - Algumas maneiras comuns de configurar predicados incluem:
       - Path: Roteia com base no caminho da URL. Exemplo: Path=/api/**.
       - Method: Roteia com base no método HTTP (GET, POST, etc.).
       - Header: Roteia com base em cabeçalhos HTTP específicos.
       - Query: Roteia com base em parâmetros de consulta.
  - Filters (Filtros)
       - Filtros permitem modificar a requisição ou a resposta. Exemplos incluem:
       - AddRequestHeader: Adiciona um cabeçalho à requisição.
       - AddResponseHeader: Adiciona um cabeçalho à resposta.
       - ModifyResponseBody: Modifica o corpo da resposta.
       - Retry: Tenta novamente uma requisição em caso de falha.
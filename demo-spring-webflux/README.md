# Programação Reativa é um paradigma de programação orientado a fluxos de dados e a propagação de mudanças.

- Corrigindo erro ao acessar H2 Console
  - https://hantsy.github.io/spring-reactive-sample/data/data-r2dbc.html
  - https://medium.com/@padiahrohit/enable-h2-console-in-java-reactive-environmant-dcfcfdd6858a

# Documentação de Programação Reativa
 - [x] [Reactive Programming](https://en.wikipedia.org/wiki/Reactive_programming)
 - [x] [Reactive Streams](https://www.reactive-streams.org/)
 - [x] [Project Reactor](https://projectreactor.io/)
 - [x] [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
 - [x] [Spring Data Reactive](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.reactive)

# Conceitos
- [x] Publisher
  - O que é um Publisher?
    - Publisher é uma fonte de dados.
    - Publisher é uma fonte de dados que emite itens.
    - Publisher é uma fonte de dados que emite itens e que pode emitir um erro ou completar.
    - Publisher é uma fonte de dados que emite itens e que pode emitir um erro ou completar e que pode ser processada por um ou mais Subscribers. 
- [x] Subscriber
 - O que é um Subscriber?
   - Subscriber é um consumidor de dados.
   - Subscriber é um consumidor de dados que consome itens.
   - Subscriber é um consumidor de dados que consome itens e que pode consumir um erro ou completar.
   - Subscriber é um consumidor de dados que consome itens e que pode consumir um erro ou completar e que pode ser processado por um ou mais Publishers. 
- [x] Subscription
 - O que é um Subscription?
   - Subscription é uma associação entre um Publisher e um Subscriber.
   - Subscription é uma associação entre um Publisher e um Subscriber que permite que o Subscriber solicite itens ao Publisher.
   - Subscription é uma associação entre um Publisher e um Subscriber que permite que o Subscriber solicite itens ao Publisher e que permite que o Publisher envie itens ao Subscriber.
   - Subscription é uma associação entre um Publisher e um Subscriber que permite que o Subscriber solicite itens ao Publisher e que permite que o Publisher envie itens ao Subscriber e que permite que o Publisher envie um erro ou complete a Subscription. 
- [x] Processor
 - O que é um Processor?
   - Processor é uma associação entre um Publisher e um Subscriber.
   - Processor é uma associação entre um Publisher e um Subscriber que permite que o Subscriber solicite itens ao Publisher.
   - Processor é uma associação entre um Publisher e um Subscriber que permite que o Subscriber solicite itens ao Publisher e que permite que o Publisher envie itens ao Subscriber.
   - Processor é uma associação entre um Publisher e um Subscriber que permite que o Subscriber solicite itens ao Publisher e que permite que o Publisher envie itens ao Subscriber e que permite que o Publisher envie um erro ou complete a Subscription. 
- [x] Flux
  - O que é um Flux?
    - Flux é uma sequência de 0 a N itens.
    - Flux é um Publisher que emite 0 a N itens.
    - Flux é um Publisher que emite 0 a N itens e que pode emitir um erro ou completar.
    - Flux é um Publisher que emite 0 a N itens e que pode emitir um erro ou completar e que pode ser processado por um ou mais Subscribers. 
- [x] Mono
 - O que é um Mono?
   - Mono é uma sequência de 0 ou 1 item.
   - Mono é um Publisher que emite 0 ou 1 item.
   - Mono é um Publisher que emite 0 ou 1 item e que pode emitir um erro ou completar.
   - Mono é um Publisher que emite 0 ou 1 item e que pode emitir um erro ou completar e que pode ser processado por um ou mais Subscribers. 


# Conceitos de Programação Reativa
 - Características
   - [x] Fluxo de Dados
    - O que é um fluxo de dados?
      - Fluxo de dados é uma sequência de itens que são emitidos por um Publisher e que são consumidos por um Subscriber.
      - Fluxo de dados é uma sequência de itens que são emitidos por um Publisher e que são consumidos por um Subscriber e que podem ser processados por um ou mais Publishers e Subscribers.
      - Fluxo de dados é uma sequência de itens que são emitidos por um Publisher e que são consumidos por um Subscriber e que podem ser processados por um ou mais Publishers e Subscribers e que podem ser transformados, filtrados, combinados, agrupados, etc. 
   - [x] Baseado em Project Reactor
   - [x] Programação Reativa
   - [x] Não bloqueante
    - O que é ser não bloqueante?
      - Não bloqueante significa que a thread que está processando a requisição não fica parada esperando o resultado de uma operação de I/O.
      - A thread que está processando a requisição é liberada para processar outras requisições.
      - Quando a operação de I/O é concluída, a thread é reutilizada para processar o resultado da operação.
   - [x] Assíncrono
    - O que é ser assíncrono?
      - Assíncrono significa que a thread que está processando a requisição não fica parada esperando o resultado de uma operação de I/O.
      - A thread que está processando a requisição é liberada para processar outras requisições.
      - Quando a operação de I/O é concluída, a thread é reutilizada para processar o resultado da operação. 
   - [x] Baseado em eventos
   - [x] Baseado em rotas
   - [x] Baseado em anotações
   - [x] Backpressure
     - O que é backpressure?
       - Backpressure é um mecanismo que permite que o Subscriber controle a quantidade de itens que ele deseja receber do Publisher.
       - Backpressure é um mecanismo que permite que o Subscriber controle a quantidade de itens que ele deseja receber do Publisher para evitar que o Publisher envie mais itens do que o Subscriber pode processar.
       - Backpressure é um mecanismo que permite que o Subscriber controle a quantidade de itens que ele deseja receber do Publisher para evitar que o Publisher envie mais itens do que o Subscriber pode processar e que permite que o Subscriber solicite mais itens ao Publisher quando ele estiver pronto para processar mais itens. 


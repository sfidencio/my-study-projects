# explorando-rest-api-springboot

>Este projeto aborda os seguintes tópicos:
 - [x] Rest API com Spring Boot.
 - [x] Diferentes maneiras de expor os endpoints da API.
 - [x] Retornos Http corretos para cada operação da API.
 - [x] Produzindo e consumindo JSON, XML e outros formatos.
 
> ## Algumas dicas:
 - [x] Se utilizarmos o ResponseEntity ao inves de retornar o objeto diretamente, podemos ter mais controle sobre o retorno da API, como por exemplo, retornar um status code diferente de 200.
   - [x] ResponseEntity.ok() retorna o status code 200.
   - [x] ResponseEntity.created() retorna o status code 201.
     - [x] ResponseEntity.created() poderia ser substituido por ResponseEntity.status(HttpStatus.CREATED).
   - [x] ResponseEntity.noContent() retorna o status code 204.
   - [x] ResponseEntity.badRequest() retorna o status code 400.
   - [x] ResponseEntity.notFound() retorna o status code 404.
 - [x] Para retornar um objeto em formato JSON, basta adicionar a anotacao @ResponseBody no metodo que retorna o objeto, caso estejamos utilizando a anotacao @Controller, caso contrario, se estivermos utilizando a anotacao @RestController, nao e necessario adicionar a anotacao @ResponseBody, pois o @RestController ja faz isso automaticamente.
 - [x] Nao confundamos o @ResponseBody com o @RequestBody, o @RequestBody e utilizado para receber um objeto no formato JSON e transforma-lo em um objeto Java.
 - [x] Para retornar um objeto em formato XML, basta adicionar a anotacao @JacksonXmlRootElement na classe do objeto que sera retornado, alterar o @produces para "application/xml" e adicionar a dependencia do jackson-dataformat-xml no pom.xml.
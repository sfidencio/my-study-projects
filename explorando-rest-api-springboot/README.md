# explorando-rest-api-springboot

>Lista de tarefas abordadas no projeto:
 - Rest API com Spring Boot.
 - Diferentes maneiras de expor os endpoints da API.
 - Retornos Http corretos para cada operação da API.
 - Produzindo e consumindo JSON, XML e outros formatos.
 

>Algumas dicas:
 - Se utilizarmos o ResponseEntity ao inves de retornar o objeto diretamente, podemos ter mais controle sobre o retorno da API, como por exemplo, retornar um status code diferente de 200.
 - ResponseEntity.ok() retorna o status code 200.
 - ResponseEntity.created() retorna o status code 201.
 - ResponseEntity.created() poderia ser substituido por ResponseEntity.status(HttpStatus.CREATED), essa premissa vale para todos os outros metodos ou retornos.
 - ResponseEntity.noContent() retorna o status code 204.
 - ResponseEntity.badRequest() retorna o status code 400.
 - ResponseEntity.notFound() retorna o status code 404.
 - Para retornar um objeto em formato JSON, basta adicionar a anotacao @ResponseBody no metodo que retorna o objeto, caso estejamos utilizando a anotacao @Controller, caso contrario, se estivermos utilizando a anotacao @RestController, nao e necessario adicionar a anotacao @ResponseBody, pois o @RestController ja faz isso automaticamente.
 - Nao confundamos o @ResponseBody com o @RequestBody, o @RequestBody e utilizado para receber um objeto no formato JSON e transforma-lo em um objeto Java.
 - Para retornar um objeto em formato XML, basta adicionar a anotacao @JacksonXmlRootElement na classe do objeto que sera retornado, alterar o @produces para "application/xml" alem de  adicionar a dependencia do jackson-dataformat-xml no pom.xml.
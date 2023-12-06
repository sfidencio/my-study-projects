
# explorando-rest-api-springboot
>[!IMPORTANT]
>Este projeto aborda os seguintes tópicos:
> - [x] Criar "controller" Rest
>   - [x] Compreendendo as diferencas entre @Controller e @RestController
>     - RestController é uma especialização de Controller, que já possui a anotação @ResponseBody em todos os seus métodos
>     - @ResponseBody é uma anotação que indica que o retorno do método deve ser vinculado ao corpo da resposta HTTP
>     - @RequestBody é uma anotação que indica que o parâmetro do método deve ser vinculado ao corpo da requisição HTTP
>       - Exige que o `Content-Type` da requisição seja `application/json` e recebe um objeto JSON que será convertido para o objeto Java
>   - [x] Consumer/Producer JSON e XML e etc
>   - [x] @GetMapping
>   - [x] @PostMapping
>   - [x] @PutMapping
>   - [x] @DeleteMapping
>   - [x] @PatchMapping
>   - [x] @RequestMapping
>   - [x] @PathVariable
>   - [x] @RequestParam
>   - [x] @RequestBody
>   - [x] @ResponseStatus
>   - [x] ResponseEntity<T>
>     - [x] Reprensenta o retorno de uma requisição HTTP, nele voce pode encapsular o objeto de retorno, o status HTTP e os headers
> - [x] Mapeamento de recursos e subrecursos baseado no fundamentos RestFul
>   - Implementação de multiplas rotas para um mesmo recurso ou metodo
> - [x] Tratamento de erros
> - [x] Operacoes de POST, DELETE, GET, PUT, PATCH e retorno de codigos de status de acordo com cada verbo
> - [x] Explorando Lombok
> - [x] DevTools do Spring no IntelliJ
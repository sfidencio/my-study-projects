# dicas-macetes-ferramentas

> [!IMPORTANT]
> Lista de dicas, macetes e ferramentas que podem ser úteis no dia a dia de um desenvolvedor.

- Pesquisar solução de código em vários repositórios do git, a partir de um único ponto?
   - https://sourcegraph.com/search (indexed)
     
- Configurar Swagger SpringBoot usando SpringDoc (SpringFox Descontinuado)
   - https://www.bezkoder.com/spring-boot-swagger-3/
     
- Dicas MacOS:
   - Instalar SDKMan for MacOS - Gerenciar instalações JAVA
   -  https://sdkman.io/install
   -  Repetição de teclas:
      - Executar no terminal
         - defaults write -g ApplePressAndHoldEnabled -bool false
   - Instalar Shottr (PrintScreen, ScreenShots)
      - https://shottr.cc/ 
   - Trabalhar com Clipboard (Instalar o Maccy)
      - https://maccy.app/
   - Instalar rectangle para gestão de janelas
      - https://rectangleapp.com/
   - Configuração do ohMyzsh + PowerLevel(p10k)
      - [https://github.com/sfidencio/my-study-projects/tree/master/explorando-configuracao-ambiente-windows-wsl2-ubuntu-shell-zshrc (procure](https://v-char.medium.com/now-let-customize-your-native-macos-terminal-with-oh-my-zsh-and-powerlevel10k-b48b9c30d39f)
   - Configurando iTerm (Enable Transparency)
      - https://iterm2.com/
   - Atalhos
      - `Option + E` Acento agudo
      - `Shift + 6` Acento circunflexo
      - `Option + N` Til
      - `Option + C` Cedilha 

- [Instalação e Configuração do IntelliJ embarcado no WSL2](#instalação-e-configuração-do-intellij-embarcado-no-wsl2)
- [Quer evoluir funcionalidades em uma aplicação por meio de features?](#quer-evoluir-funcionalidades-em-uma-aplicação-por-meio-de-features)
- [Como eu envio a URI do recurso recém-criado via verbo POST, e o status CREATED/201 no springboot?](#como-eu-envio-a-uri-do-recurso-recém-criado-via-verbo-post-e-o-status-created201-no-springboot)
- [Como gravar json em um campo do tipo json do banco de dados. (Uso)](#como-gravar-json-em-um-campo-do-tipo-json-do-banco-de-dados-uso)
- [Implementando um Mapper pra converter Map<?,?> para campo tipo json/text, usando JPA/hibernate:](#implementando-um-mapper-pra-converter-map-para-campo-tipo-jsontext-usando-jpahibernate)
- [Dicas Lombok:](#dicas-lombok)
- [Dicas sobre Logging no java](#dicas-sobre-logging-no-java)
- [Lombok não funciona corretamente com MapStruct, quando envolve a feature record do java 17+? então veja o artigo abaixo:](#lombok-não-funciona-corretamente-com-mapstruct-quando-envolve-a-feature-record-do-java-17-então-veja-o-artigo-abaixo)
- [Dicas IntelliJ - Manipulação de arquivos json por exemplo](#dicas-intellij---manipulação-de-arquivos-json-por-exemplo)
- [Dicas de como designar um tipo ENUM para deserializar uma string vazia (Spring Boot)](#dicas-de-como-designar-um-tipo-enum-para-deserializar-uma-string-vazia-spring-boot)
- [Básico do GIT](#básico-do-git)
- [Implementando flyway](#implementando-flyway)
- [Criando projeto springcloud em modulos](#criando-projeto-springcloud-em-modulos)
- Use o jetbrainsToolbox, facita a gestão de ferramentas e permite usar a versão EAP do intelliJ ultimate.
    - https://www.jetbrains.com/toolbox-app/download/download-thanks.html?platform=windows
- [Padrões de URI](#padrões-de-uri)
- [Entendendo Idempotência](#entendendo-idempotência)
- Instale o Git Copilot, e o tema Git Copilot Dark Them no InteliiJ, na seção plugins.
- Considere o uso do SDKMAN para gestão de versões da JDK no java.
    - https://sdkman.io/
- Deploy continuo na AWS via github actions
    - https://www.youtube.com/watch?v=mIuFF_ZP_60
- Gerenciamento usuário com KeyCloak
    - https://www.youtube.com/watch?v=wgdo5I53GQo
- [Dicas GIT](#dicas-git)
- [Paginacao Spring Data](#paginacao-spring-data)
      
       


# Instalação e Configuração do IntelliJ embarcado no WSL2
    - https://dev.to/janetmutua/installing-jetbrains-toolbox-on-ubuntu-527f
    - https://github.com/AppImage/AppImageKit/wiki/FUSE
    - https://dev.to/wesleyotio/configurando-wsl2-com-intellij-2pl7
        - Dica interessante sobre a disponibilização dos atalhos no windows. 
    - https://learn.microsoft.com/en-us/windows/wsl/tutorials/gui-apps#install-support-for-linux-gui-apps
        - Instalar o `x11-apps`.     
# Implementação de Spring-HATEOS
    - https://grapeup.com/blog/how-to-build-hypermedia-api-with-spring-hateoas/#

# Quer evoluir funcionalidades em uma aplicação por meio de features?
    - https://openfeature.dev/specification/

# Como eu envio a URI do recurso recém-criado via verbo POST, e o status CREATED/201 no springboot?
```java
  @PostMapping("/mesas/{id}/reservas")
    @Transactional
    public ResponseEntity<?> reservar(
            @PathVariable(value = "id") Long mesaId,
            @RequestBody ReservaMesaRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        // lógica para reservar a mesa

        URI location = uriBuilder.path("/mesas/{id}/reservas/{reservaId}")
                .buildAndExpand(mesa.getId(), reserva.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
```
  - Neste exemplo, a URI é construída com base no padrão /mesas/{id}/reservas/{reservaId}, onde {id} é o identificador da mesa e {reservaId} é o identificador da reserva. Ao chamar uriBuilder.path(...).buildAndExpand(...).toUri(), a URI é construída substituindo os placeholders pelos valores reais.
  Ao retornar ResponseEntity.created(location).build(), você está enviando uma resposta 201 CREATED com o cabeçalho Location contendo a URI do recurso recém-criado.
  Esse é um exemplo comum de uso de URI na resposta CREATED no Spring Boot para indicar a localização do recurso criado.

# Como gravar json em um campo do tipo json do banco de dados. (Uso)
  ```java
    @ColumnTransformer(write = "?::jsonb")
    @Column(name = "field_json", nullable = false, columnDefinition = "jsonb")
    private Map<String, String> valores;
  ```

# Implementando um Mapper pra converter Map<?,?> para campo tipo json/text, usando JPA/hibernate: 

```java

//Uso
@Convert(converter = MapConverter.class)
private Map<String, String> valores;

//Implementação do converter
public class MapConverter implements AttributeConverter<Map<String, String>, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
```

# Dicas Lombok:
    - O Lombok é uma biblioteca muito útil para reduzir a verbosidade do código Java, evitando a escrita de getters, setters, construtores, entre outros métodos padrão. Além das funcionalidades básicas, o Lombok oferece recursos avançados que podem ser configurados para atender às necessidades específicas do seu projeto.
Aqui estão algumas configurações avançadas do Lombok que você pode utilizar:

          - @Builder: A anotação @Builder gera um padrão Builder para a classe, permitindo a criação de objetos de forma mais fluente e legível.

          - @SneakyThrows: A anotação @SneakyThrows permite lançar exceções verificadas sem a necessidade de declará-las no método ou no throws clause.

          - @Synchronized: A anotação @Synchronized adiciona sincronização ao método ou bloco de código em que é aplicada.

          - @Cleanup: A anotação @Cleanup é usada para garantir a limpeza de recursos automaticamente, como fechamento de streams.

          - @AllArgsConstructor: A anotação @AllArgsConstructor gera um construtor que inicializa todos os campos da classe.

    - Para habilitar esses recursos avançados do Lombok, você precisa configurar adequadamente o plugin do Lombok em sua IDE e adicionar as dependências corretas em seu projeto. Além disso, é importante estar ciente de como cada recurso funciona e como aplicá-los corretamente em suas classes.

# Dicas sobre Logging no java

> Caso queira visualizar os log's em niveis de DEBUG e TRACE, sem a necessidade de configurar detalhadamente cada pacote, pasta setar no root.
```yaml
logging:
  level:
    # Default é INFO, mas pra facilitar troubleshooting, usa DEBUG e TRACE
    # Principalmente pra checar valores setados no banco de dados
    # Nesse cenário mostra tudo de todos pacotes, nao precisa configurar detalhadamente cada pacote
    # TRACE ja inclui DEBUG e INFO
    root: ${LOG_LEVEL_ROOT:TRACE}
```

# Lombok não funciona corretamente com MapStruct, quando envolve a feature `record` do java 17+? então veja o artigo abaixo:
 - https://springframework.guru/using-mapstruct-with-project-lombok/

# Dicas IntelliJ - Manipulação de arquivos json por exemplo

  - https://www.jetbrains.com/help/idea/mastering-keyboard-shortcuts.html
  - Selecionar todas as ocorrencias de uma palavra: `ctrl + shift + alt + j`
  - Pressupomos que precisamos extrair apenas o campo "id" do arquivo abaixo, com ajuda da IDE + Regex podemos fazer isso facilmente:

```json
[
  {
    "id": 1,
    "nome": "João",
    "idade": 20
  },
  {
    "id": 2,
    "nome": "Maria",
    "idade": 30
  },
  {
    "id": 3,
    "nome": "José",
    "idade": 40
  }
]
```

- Com arquivo acima aberto na IDE podemos fazer o seguinte:
-  selecione com mouse o campo "id" de um dos objetos, da seguinte forma:
  - ![img_1.png](img/img_1.png)
  - O segredo é, tem que ter um padrão, e esse padrão tem que se repetir em todos os objetos, no caso acima o padrão
      é: `"id".
 - Caso esse padrão não se repita, não tem como fazer isso, pois a IDE não vai conseguir identificar o padrão.
 - pressione `ctrl + shift + alt + j` para selecionar todas as ocorrencias de "id"
 - Deve ficar da seguinte forma a seleção:
 - ![img_2.png](img/img_2.png)
 - Uma vez que o padrão de seleção foi identificado, então com `shift` pressionado, continue a seleção usando as setas do
  teclado, para cima ou para baixo, até que todas as ocorrencias sejam selecionadas.
 - pressione `ctrl + c` para copiar, e cole em outro arquivo, deve ficar da seguinte forma:
  ```text
   "id": 1
   "id": 2
   "id": 3
  ```
 - Pressione `ctrl + r` para abrir a janela de substituição, e no campo "Text to find" digite: `$`, em expressão
    regular, esse caracter indica que a ocorrência está no final da linha e no campo "Replace with" digite por
    exemplo: `;`, ou seja vamos inserir virgula no final.
 - Deve ficar da seguinte forma:
  ```text
   "id": 1,
   "id": 2,
   "id": 3,
  ```
 - Finalmente, selecione todo texto e faça um "join" de linha, pressionando `ctrl + shift + j`, deve ficar daseguinte forma:
  ```text
   "id": 1, "id": 2, "id": 3,
  ```
 - Pronto, agora é só copiar e colar onde precisar.

### Dicas de como designar um tipo ENUM para deserializar uma string vazia (Spring Boot)

> 1. Primeiro crie um tipo ENUM com um atributo do tipo String, e um construtor que recebe esse atributo, exemplo:

```java
 public enum TipoEnum {
    TIPO_1("tipo_1"),
    EMPTY("");

    private String tipo;

    TipoEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
 ```

> 2. Agora crie um deserializer para esse tipo ENUM, que espera uma string vazia da response, exemplo:

```java
@Component
public class TipoEnumDeserializer extends JsonDeserializer<TipoEnum> {
    @Override
    public TipoEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String tipo = jsonParser.getValueAsString();
        if (StringUtils.isEmpty(tipo)) {
            return TipoEnum.EMPTY;
        }
        return TipoEnum.valueOf(tipo);
    }
}
```

> 3. Agora vamos registrar esse deserializer ObjectMapper para ser injetado no contexto, exemplo:

```java
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TipoEnum.class, new TipoEnumDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
```

> 5. Pronto, agora é só usar o tipo ENUM no seu DTO que vai ser feito "binding" na `response`, exemplo:

```java
   public class MyDTO {
    @JsonDeserialize(using = TipoEnumDeserializer.class)
    private TipoEnum tipo;
}
```

> 6. E o tipo ENUM será deserializado corretamente, mesmo que a response venha com uma string vazia.
```json
{
  "tipo": ""
}
```

# Básico do GIT

### Comandos git para associar o repositório local ao repositório remoto
```bash
git remote add origin URL_DO_REPOSITORIO
git remote -v 
git branch -M main
git push -u origin main
```
### Comandos git para atualizar o repositório local com o repositório remoto
```bash
git pull origin main
```
### Comandos git para atualizar o repositório remoto com o repositório local
```bash
git add .
git commit -m "Mensagem do commit"
git push origin main/master
```
### Comandos git para criar uma nova branch
```bash
git checkout -b nome_da_branch
```
### Comandos git para listar as branches
```bash
git branch
```
### Comandos git para mudar de branch
```bash
git checkout nome_da_branch
```
### Comandos git para deletar uma branch
```bash
git branch -d nome_da_branch
```
### Comandos git para deletar uma branch remota
```bash
git push origin --delete nome_da_branch
```

#### Links sobre git
    - https://github.com/joshnh/Git-Commands


# Implementando flyway
 -  [Implementando flayway](https://medium.com/hprog99/set-up-flyway-with-spring-boot-1b24b8abe56e)https://medium.com/hprog99/set-up-flyway-with-spring-boot-1b24b8abe56e
 -  https://www.baeldung.com/database-migrations-with-flyway
```yaml
spring:
  flyway:
    enabled: true
    baseline-on-migrate: true
    #url: jdbc:postgresql://localhost:5432/db
    #user: postgres
    #password: '1234'
    baseline-version: 1
    baseline-description: 'Baseline version'
    locations: classpath:db/migration
    schemas: public
    table: schema_version
    validate-on-migrate: true
```

```xml
         <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
            <version>${flyway.version}</version>
        </dependency>
```

![image](https://github.com/sfidencio/my-study-projects/assets/660615/597247d5-77be-4294-8639-ffc5ca0836d8)

```text
V<version>__<description>.sql for versioned scripts
U<version>__<description>.sql for undo scripts
R__<description>.sql for repeatable scripts
```


# Criando projeto springcloud em modulos

- https://medium.com/@AlexanderObregon/step-by-step-creating-your-first-spring-cloud-application-ef4dcfe277a4
  

# Padrões de URI

Sure! Here are some common URI patterns used in RESTful APIs along with examples:

1. *Resource Collection URI:*
   - Pattern: /resource
   - Example: /users
   - Description: Represents a collection of a specific type of resource.

2. *Resource Element URI:*
   - Pattern: /resource/{id}
   - Example: /users/123
   - Description: Represents a specific element within a collection, identified by its unique identifier.

3. *Nested Resource URI:*
   - Pattern: /resource/{id}/nested-resource
   - Example: /users/123/orders
   - Description: Represents a nested resource related to a specific resource element.

4. *Resource Sub-collection URI:*
   - Pattern: /resource/{id}/sub-resource
   - Example: /users/123/addresses
   - Description: Represents a sub-collection of resources related to a specific resource element.

5. *Resource Action URI (Custom Actions):*
   - Pattern: /resource/{id}/action
   - Example: /users/123/reset-password
   - Description: Represents a custom action or operation that can be performed on a specific resource element.

6. *Search or Filter URI:*
   - Pattern: /resource?parameter=value
   - Example: /users?role=admin
   - Description: Represents a search or filter operation on a collection of resources based on specified criteria.

7. *Paginated Resource URI:*
   - Pattern: /resource?page={page}&size={size}
   - Example: /users?page=1&size=10
   - Description: Represents paginated results for a collection of resources, allowing clients to navigate through large result sets.

These URI patterns provide a structured and consistent way to design the endpoints of your RESTful API. By following these patterns, you can create a clear and intuitive API structure that is easy to understand and use. Remember to choose URI patterns that best fit the resources and operations of your API and document them properly for API consumers.

# Entendendo Idempotência

Os verbos HTTP que são idempotentes são GET, HEAD, PUT e DELETE. Isso significa que esses verbos podem ser executados várias vezes sem alterar o estado do servidor além da primeira requisição. Vou exemplificar o uso desses verbos idempotentes no Spring Boot:

1. *GET*:
   - O verbo GET é usado para recuperar informações do servidor e é idempotente, ou seja, fazer várias solicitações GET para o mesmo recurso não deve alterar o estado do servidor. Um exemplo de uso no Spring Boot seria:

java
@GetMapping("/api/books/{id}")
public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    // Lógica para recuperar um livro com o ID fornecido
    Book book = bookService.getBookById(id);
    return ResponseEntity.ok(book);
}


2. *HEAD*:
   - O verbo HEAD é semelhante ao GET, mas retorna apenas os cabeçalhos da resposta, sem o corpo da resposta. Também é idempotente. Um exemplo no Spring Boot seria semelhante ao método GET acima, mas retornando apenas os cabeçalhos.

3. *PUT*:
   - O verbo PUT é usado para atualizar um recurso no servidor e é idempotente, ou seja, a mesma solicitação PUT pode ser feita várias vezes sem efeitos colaterais. Um exemplo de uso no Spring Boot seria:

java
@PutMapping("/api/books/{id}")
public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
    // Lógica para atualizar o livro com o ID fornecido
    Book book = bookService.updateBook(id, updatedBook);
    return ResponseEntity.ok(book);
}


4. *DELETE*:
   - O verbo DELETE é usado para remover um recurso do servidor e é idempotente, ou seja, excluir um recurso várias vezes não deve alterar o estado do servidor após a primeira requisição. Um exemplo de uso no Spring Boot seria:

java
@DeleteMapping("/api/books/{id}")
public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    // Lógica para excluir o livro com o ID fornecido
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
}


Esses são exemplos de como os verbos HTTP idempotentes (GET, HEAD, PUT e DELETE) podem ser utilizados no Spring Boot para realizar operações de leitura, atualização e exclusão de recursos de forma segura e consistente, sem causar efeitos colaterais indesejados no servidor.
O método POST no protocolo HTTP não é considerado idempotente devido à sua natureza de criação ou modificação de recursos no servidor. A característica de idempotência de um método HTTP significa que realizar a mesma operação várias vezes produzirá o mesmo resultado, sem efeitos colaterais adicionais além da primeira requisição.

Aqui estão algumas razões pelas quais o método POST não é idempotente:

1. *Criação de Recursos*: O método POST é comumente usado para criar novos recursos no servidor. Cada vez que uma requisição POST é feita com os mesmos dados, um novo recurso é criado no servidor, resultando em um estado diferente a cada vez.

2. *Efeitos Colaterais*: Uma requisição POST pode ter efeitos colaterais, como a geração de um identificador único para o recurso criado, a atualização de contadores ou a execução de ações específicas associadas à criação do recurso. Esses efeitos colaterais podem tornar as requisições POST não idempotentes.

3. *Operações de Modificação*: Além da criação de recursos, o método POST também pode ser usado para realizar operações de modificação em recursos existentes, como atualizações parciais ou a execução de ações específicas. Essas operações podem alterar o estado do recurso a cada requisição.

4. *Segurança e Integridade dos Dados*: Em alguns casos, é importante que certas operações não sejam idempotentes para garantir a segurança e a integridade dos dados. Por exemplo, uma transação financeira não deve ser idempotente para evitar cobranças duplicadas.

Em resumo, o método POST no protocolo HTTP não é idempotente porque cada requisição POST pode resultar em uma mudança de estado no servidor, seja criando um novo recurso, modificando um recurso existente ou realizando ações específicas associadas à requisição. Portanto, é importante ter cuidado ao usar o método POST para garantir que as operações realizadas sejam apropriadas e não causem efeitos colaterais indesejados.

# Dicas GIT
https://www.horadecodar.com.br/2021/07/23/como-desfazer-um-git-merge-no-repositorio-local/ 

### Como remover arquivos da área de stage 
```bash
git status 
git reset 
```

### script .bashrc para log in mais rápido nas rotinas git:  
```bash
var1=" https://USUARIO:SENHA@github/PROJETO.git" 
```

### Update pode ser qualquer nome, função do git fetch e git pull 
```bash
alias update='git fetch $var1 && git pull $var1' 
```

push pode ser qualquer nome, função do git push 
```bash
alias push='git push $var1' 
```

like 1 
 
### Como desfazer modificações não salvas 

```bash
git status 
git reset 
git clean -df 
git checkout -- . 
```

### Como desfazer o último commit  - desfazer último commit sem desfazer as modificações nos arquivos: 

```bash
git status 
git reset --soft HEAD~1 
```

### Como deletar commits e também 

### Modificações nos arquivos 
### Voltar o projeto ao estado de um dado commit (deletar commits e alterações posteriores a esse commit) 

```bash
git status 
git reset --hard <código do commit> 
```

### ATENÇÃO: ação destrutiva! 


### Como atualizar o repositório local em relação ao remoto 

```bash
git status 
git pull <nome do remote> <nome do branch> 
```

### Como resolver push rejeitado 
### Não é permitido enviar um push se seu repositório local está atrasado em 
### Relação ao histórico do repositório remoto! Por exemplo: 

```bash
git pull <nome do remote> <nome do branch> 
```

### Você tem que atualizar o repositório local: 

### Resolvendo conflito 

 - Analise o código fonte 
 - Faça as edições necessárias 
 - Faça um novo commit 

 

### Como sobrescrever um histórico no Github 
```bash
git push -f <nome do remote> <nome do branch> 
```

### ATENÇÃO: ação destrutiva! 
   - Como apontar o projeto para outro 
   - repositório remoto 

```bash
git remote set-url origin git@github.com:seuusuario/seurepositorio.git 
```

### Stash - Backup de Workspace  
```bash
git stash push -m "my_stash" 
git stash list 
git stash pop stash@{n} 
git stash apply stash@{n} -> aplicar stash e manter salvo 
git stash apply my_stash 
git restore . 
```

   - https://ohshitgit.com/ 

### Como alterar mensagens antigas de commit 
```bash
git rebase -i HEAD~n 
```

### Onde n e o numero de commits q deseja voltar 
 
### Para altera o commit inicial e todos os outros 
```bash
git rebase -i --root  
```

### Continuar edição dos commits 
```bash
git rebase --edit-todo 
 ```

### Vai aparecer "pick"...e so alterar para "reword"..o texto do commit que deseja alterar,..fazendo isso salve, (ESC^wq!).. Logo, vai ser aberto o arquivo do commit especifico que colocaste "reword"..entao proceda com a alteracao. 
 

### Caso queira alterar, somente o ultimo commit,..utilizo o --amend no commit assim: 

git commit --amend  

### Voltar commit preservando as modificações atuais. 
```bash
git log –oneline  
```

### Pega o hash md5 do commit desejado 

```bash
git reset –mixed HASH_COMMIT  
git add . && git commit –m "NUMERO_CAD: ...." --amend –no-edit 0362804
```

### Apagando e persistindo credenciais de acessos git 
```bash
git config --global --unset credential.helper 
git config --global credential.helper store
```

# Vinculando repository GIT e empurrando codigo

```bash
git init
git remote add origin <URL_do_repositorio_remoto>
git add .
git commit -m "Mensagem do commit"
git push -u origin main
```

# Paginacao Spring Data
![image](https://github.com/sfidencio/my-study-projects/assets/660615/23d09292-31ff-44ef-a0d6-2cd0f34301aa)

![image](https://github.com/sfidencio/my-study-projects/assets/660615/1e726608-241d-4e68-b6c2-a8cb4ddb2310)

![image](https://github.com/sfidencio/my-study-projects/assets/660615/28ed5092-986b-4f9c-9b59-c330448549c1)

![image](https://github.com/sfidencio/my-study-projects/assets/660615/8de31309-ad8c-4202-a9b4-4c73dbe27ab3)




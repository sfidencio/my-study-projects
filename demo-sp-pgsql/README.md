# Explorando chamada de procedures/funções no pgsql com Spring Boot (JDBCTemplate)

## Objetivo
O objetivo deste documento é apresentar um exemplo de chamada de procedures/funções no PostgreSQL utilizando Spring Boot e JDBCTemplate.

## Tecnologias
- Java 17
- Spring Boot
- PostgreSQL
- JDBCTemplate
- Maven
- Docker


## Criando o banco de dados
- Na pasta `code` temos os scripts para criação do banco de dados e das tabelas bem como a stored procedure/função que serão chamadas no exemplo.

## Principais pontos do código
- Camada de Acesso a Dados (DAO)
- Utilização do JDBCTemplate para chamada de procedures/funções
```java
/*
 *Atenção, quando usamos o nivel de isolamento
 *
 *READ_COMMITTED: Este é o nível de isolamento padrão no caso do PostgreSQL.
 *
 *Ele garante que uma transação só possa ver dados que foram commitados por outras transações.
 *O nivel de isolamento READ_COMMITTED não é pessimista, ele é otimista, porque
 *ele não trava as linhas que estão sendo lidas, ele apenas verifica se houve alguma alteração.
 *Em um SELECT, ele faz lock apenas na linha que está sendo lida, e não na tabela inteira.
 *
 *
 *SERIALIZABLE: Este é o nível de isolamento mais alto no PostgreSQL.
 *Ele garante que uma transação só possa ver dados que foram commitados por outras transações.
 *Esse nivel de isolamento é pessimista, porque ele trava as linhas que estão sendo lidas, podendo
 *causar deadlocks ou baixa performance na aplicação.
 *Em um select, ele faz lock na tabela inteira.
 *
 *READ_UNCOMMITTED: Este é o nível de isolamento mais baixo no PostgreSQL.
 *Ele permite que uma transação veja dados que foram modificados por outras transações, mas que ainda não foram commitados.
 *
 *
 *É simples entender se o spring esta adotando o nivel de isolamento correto conforme a configuração,
 *basta ligar o log em modo TRACE, e procurar por "Setting JDBC Connection [com.zaxxer.hikari.HikariProxyConnection@xxxxxx] transaction isolation to 2"
 *Caso seja READ_COMMITTED, o nivel de isolamento esta correto, caso contrario, o nivel de isolamento esta errado.
 *
 *Para SERIALIZABLE, o nivel de isolamento deve ser 8
 **/

@Repository
@RequiredArgsConstructor
public class UsuarioDAO implements GenericDAO<UsuarioEntity> {

    private final JdbcTemplate jdbcTemplate;

    private String getFunctionSelecionaUsuariosPorParametro() {
        var sql = new StringBuilder();
        sql.append("select ");
        sql.append("id_usuario_out AS id, ");
        sql.append("nome_usuario_out AS nome, ");
        sql.append("id_permissao_out AS id_permissao, ");
        sql.append("nome_permissao_out AS nome_permissao, ");
        sql.append("status_out AS status ");
        sql.append("from sp_seleciona_usuarios(?)");
        return sql.toString();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UsuarioEntity selecionarPorParametro(String parametro) {
        return jdbcTemplate.query(
                this.getFunctionSelecionaUsuariosPorParametro(),
                new UsuarioPermissaoResultSetExtractor(),
                parametro);
    }
}


```
## Criação da Procedure/Function no PostgreSQL
```sql
CREATE
    OR REPLACE FUNCTION sp_seleciona_usuarios(nome_in varchar)
    RETURNS TABLE
            (
                id_usuario_out     integer,
                nome_usuario_out   varchar,
                nome_permissao_out varchar,
                id_permissao_out   integer,
                usuario_id_out     integer,
                status_out         boolean
            )
AS
$$

BEGIN
    RETURN QUERY
        SELECT u.id         AS id_usuario_out,
               u.nome       AS nome_usuario_out,
               p.nome       AS nome_permissao_out,
               p.id         as id_permissao_out,
               p.usuario_id AS usuario_id_out,
               u.status     AS status_out
        FROM usuarios u
                 INNER JOIN permissoes p ON u.id = p.usuario_id
        WHERE u.nome = nome_in;
END;
$$ LANGUAGE plpgsql;
```

## Excluindo a Procedure/Function
```sql
    DROP FUNCTION sp_seleciona_usuarios(varchar);
```

## Código do ResultSetExtractor
- O ResultSetExtractor é uma interface funcional que é usada para processar o ResultSet e retornar um objeto processado.
```java
public class UsuarioPermissaoResultSetExtractor implements ResultSetExtractor<UsuarioEntity> {
    @Override
    public UsuarioEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
        Optional<UsuarioEntity> usuario = Optional.empty();
        Optional<PermissoesEntity> permissao;
        List<PermissoesEntity> permissoes = new ArrayList<>();
        while (rs.next()) {
            permissao = Optional.of(PermissoesEntity.builder()
                    .id(rs.getInt("id_permissao"))
                    .descricao(rs.getString("nome_permissao"))
                    .build());
            permissoes.add(permissao.get());
            if (rs.isLast()) {
                usuario = Optional.of(this.getUsuarioEntity(rs, permissoes));
            }
        }
        return usuario.orElse(null);
    }

    private UsuarioEntity getUsuarioEntity(ResultSet rs, List<PermissoesEntity> permissoes) throws SQLException {
        var usuario = UsuarioEntity.builder()
                .id(rs.getInt("id"))
                .nome(rs.getString("nome"))
                .status(rs.getBoolean("status"))
                .permissoes(permissoes)
                .build();
        return usuario;
    }
}
```

### Configurando um JdbcTemplate customizado
- O controle de transações explícito, para aplicações de produção é de suma importância
- Deixar toda essa responsabilidade de auto-configuração a cargo do spring não permite obtermos maior otimização em cenários de alta concorrência

```java
/*
 * Eu nem precisaria de definir um JdbcTemplate customizado, pois o Spring Boot já faz isso por mim.
 * Entretanto quero ter o controle de como as transações são gerenciadas, por isso estou definindo um
 * DataSourceTransactionManager.
 */

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;



    @Bean
    public DataSource dataSource() {
        // Aqui eu poderia configurar um DataSource customizado, mas estou usando o HikariCP que é o padrão do Spring Boot.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //pega essas configurações do application.yaml
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
```
## Exemplo de chamada do endpoint
```json
GET http://localhost:8080/usuarios?nome=Pedro

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 05 Aug 2024 21:59:50 GMT

{
  "id": 5,
  "nome": "Pedro",
  "status": false,
  "permissoes": [
    {
      "id": 7,
      "descricao": "Permissão 7"
    },
    {
      "id": 8,
      "descricao": "Permissão 8"
    },
    {
      "id": 9,
      "descricao": "Permissão 9"
    },
    {
      "id": 10,
      "descricao": "Permissão 10"
    }
  ]
}
Response file saved.
> 2024-08-05T185950.200.json

Response code: 200; Time: 207ms (207 ms); Content length: 195 bytes (195 B)
```

## Referências
- [Documentação Spring](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.core)

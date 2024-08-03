package com.github.sfidencio.demosppgsql.infra.dao;

import com.github.sfidencio.demosppgsql.infra.entities.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *Preste bem atenção:
 *quando usamos o nivel de isolamento
 *
 *READ_COMMITTED: Este é o nível de isolamento padrão no PostgreSQL.
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
public class UsuarioDAO implements IUsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<UsuarioEntity> selecionarUsuarios(String nome) {
        return jdbcTemplate.query(
                SQL_SELECT_USUARIOS,
                new UsuarioResultSetExtractor(),
                nome);
    }


    private static final String SQL_SELECT_USUARIOS = "SELECT id_out," +
            "nome_out," +
            "status_out " +
            "FROM sp_seleciona_usuarios(?);";
}

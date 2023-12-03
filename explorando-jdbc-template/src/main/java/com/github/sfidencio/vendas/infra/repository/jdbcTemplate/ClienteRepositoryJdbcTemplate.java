package com.github.sfidencio.vendas.infra.repository.jdbcTemplate;

import com.github.sfidencio.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class ClienteRepositoryJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepositoryJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void salvarV2(Cliente cliente) {
        jdbcTemplate.update("insert into cliente (nome, cpf, email) values (?, ?, ?)", cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }

    public void salvar(Cliente cliente) {
        jdbcTemplate.update("insert into cliente (nome, cpf, email) values (?, ?, ?)", new Object[]{cliente.getNome(), cliente.getCpf(), cliente.getEmail()}, new int[]{java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR});
    }

    public void excluir(Integer id) {
        jdbcTemplate.update("delete from cliente where id = ?", new Object[]{id}, new int[]{java.sql.Types.INTEGER});
    }

    public void alterar(Cliente cliente) {
        jdbcTemplate.update("update cliente set nome = ?, cpf = ?, email = ? where id = ?", new Object[]{cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getId()}, new int[]{java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.INTEGER});
    }

    /*public List<Cliente> listar() {
        return jdbcTemplate.query("select * from cliente", (rs, rowNum) -> Cliente.builder()
                .id(rs.getInt("id"))
                .nome(rs.getString("nome"))
                .cpf(rs.getString("cpf"))
                .email(rs.getString("email"))
                .build());
    }*/

    public List<Cliente> listar() {
        return jdbcTemplate.query("select * from cliente", new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), null);
            }
        });
    }

    public List<Cliente> listarPorNome(String nome) {
        return jdbcTemplate.query("select * from cliente where nome like ?", new Object[]{String.format("%s", "%" + nome + "%")}, new int[]{Types.VARCHAR}, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), null);
            }
        });
    }
}

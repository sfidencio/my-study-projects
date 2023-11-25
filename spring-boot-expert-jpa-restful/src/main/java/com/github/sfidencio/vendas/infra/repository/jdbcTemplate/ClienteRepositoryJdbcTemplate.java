package com.github.sfidencio.vendas.infra.repository.jdbcTemplate;

import com.github.sfidencio.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepositoryJdbcTemplate {

    public ClienteRepositoryJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    public void salvar(Cliente cliente) {
        jdbcTemplate.update("insert into cliente (nome, cpf, email) values (?, ?, ?)", cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }
}

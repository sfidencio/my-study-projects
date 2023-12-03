package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequestValidationForJdbcTemplate;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.jdbcTemplate.ClienteRepositoryJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class ClienteServiceImp implements ClienteService {


    private final ClienteRepositoryJdbcTemplate clienteRepositoryJdbcTemplate;

    public ClienteServiceImp(ClienteRepositoryJdbcTemplate clienteRepositoryJdbcTemplate) {
        this.clienteRepositoryJdbcTemplate = clienteRepositoryJdbcTemplate;
    }

    @Override
    public void salvarJdbcTemplate(ClienteRequestValidationForJdbcTemplate clienteRequest) {
        this.clienteRepositoryJdbcTemplate.salvar(Cliente.builder()
                .cpf(clienteRequest.cpf())
                .nome(clienteRequest.nome())
                .email(clienteRequest.email())
                .build());
    }

    @Override
    public List<Cliente> listar() {
        return this.clienteRepositoryJdbcTemplate.listar();
    }

    @Override
    public List<Cliente> listarPorNome(String nome) {
        return this.clienteRepositoryJdbcTemplate.listarPorNome(nome);
    }

    @Override
    public void excluir(Integer id) {
        this.clienteRepositoryJdbcTemplate.excluir(id);
    }
    @Override
    public void alterar(Cliente cliente) {
        this.clienteRepositoryJdbcTemplate.alterar(cliente);
    }
}

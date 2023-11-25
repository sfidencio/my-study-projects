package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.jdbcTemplate.ClienteRepositoryJdbcTemplate;
import com.github.sfidencio.vendas.infra.repository.jpaentitymanager.ClienteEntityManager;
import com.github.sfidencio.vendas.infra.repository.springdatajpa.ClienteJpaRespository;
import org.springframework.stereotype.Service;

@Service
//@Transactional
public class ClienteServiceImp implements ClienteService {

    private final ClienteJpaRespository clienteJpaRespository;

    private final ClienteEntityManager clienteEntityManager;

    private ClienteRepositoryJdbcTemplate clienteRepositoryJdbcTemplate;

    public ClienteServiceImp(ClienteJpaRespository respository, ClienteEntityManager clienteEntityManager, ClienteRepositoryJdbcTemplate clienteRepositoryJdbcTemplate) {
        this.clienteJpaRespository = respository;
        this.clienteEntityManager = clienteEntityManager;
        this.clienteRepositoryJdbcTemplate = clienteRepositoryJdbcTemplate;
    }

    @Override
    public void salvarJpaRepository(ClienteRequest clienteRequest) {
        this.clienteJpaRespository.save(Cliente.builder()
                .cpf(clienteRequest.cpf())
                .nome(clienteRequest.nome())
                .email(clienteRequest.email())
                .build());
    }

    @Override
    public void salvarEntityManager(ClienteRequest clienteRequest) {
      this.clienteEntityManager.salvar(Cliente.builder()
                .cpf(clienteRequest.cpf())
                .nome(clienteRequest.nome())
                .email(clienteRequest.email())
                .build());
    }

    @Override
    public void salvarJdbcTemplate(ClienteRequest clienteRequest) {
        this.clienteRepositoryJdbcTemplate.salvar(Cliente.builder()
                .cpf(clienteRequest.cpf())
                .nome(clienteRequest.nome())
                .email(clienteRequest.email())
                .build());
    }

    @Override
    public ClienteResponse buscarPorId(Integer id) throws NotFoundException {
        return this.clienteJpaRespository.findById(id).map(c -> new ClienteResponse(c.getId(), c.getNome(), c.getCpf())).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }
}

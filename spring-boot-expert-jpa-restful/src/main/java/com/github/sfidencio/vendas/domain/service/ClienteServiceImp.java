package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.jpaentitymanager.ClienteEntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class ClienteServiceImp implements ClienteService {


    private final ClienteEntityManager clienteEntityManager;


    public ClienteServiceImp(ClienteEntityManager clienteEntityManager) {
        this.clienteEntityManager = clienteEntityManager;
    }

    @Override
    public void salvar(ClienteRequest clienteRequest) {
        this.clienteEntityManager.salvar(Cliente.builder()
                .cpf(clienteRequest.cpf())
                .nome(clienteRequest.nome())
                .email(clienteRequest.email())
                .build());
    }

    @Override
    public void alterar(ClienteRequest clienteRequest) {
        this.clienteEntityManager.alterar(Cliente.builder()
                .id(clienteRequest.id())
                .cpf(clienteRequest.cpf())
                .nome(clienteRequest.nome())
                .email(clienteRequest.email())
                .build());
    }

    @Override
    public ClienteResponse buscarPorId(Integer id) throws NotFoundException {
        var cliente = this.clienteEntityManager.buscarPorId(id);
        if (cliente == null) throw new NotFoundException("Cliente não encontrado");
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        return this.clienteEntityManager.buscarPorNome(nome);
    }

}

package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;

import java.util.List;


public interface ClienteService {

    void salvar(ClienteRequest clienteRequest);

    void alterar(ClienteRequest clienteRequest);

    List<ClienteResponse> buscarTodos();

    ClienteResponse buscarClienteEPedidos(Integer id) throws NotFoundException;
}

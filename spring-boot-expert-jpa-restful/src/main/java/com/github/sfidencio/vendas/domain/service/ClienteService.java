package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;

import java.util.List;


public interface ClienteService {

    void salvar(ClienteRequest clienteRequest) throws NotFoundException;

    void alterar(ClienteRequest clienteRequest,Integer id);

    List<ClienteResponse> buscarTodos();

    ClienteResponse buscarClienteEPedidos(Integer id) throws NotFoundException;

    void excluir(Integer id) throws NotFoundException;
}

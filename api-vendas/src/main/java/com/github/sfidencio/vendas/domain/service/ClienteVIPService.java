package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.domain.entity.mongodb.ClienteVIP;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;

import java.util.List;


public interface ClienteVIPService {

 
    ClienteVIP buscarClienteVIP(String id) throws NotFoundException;

    void salvarClienteVIP(ClienteVIP clienteVIP);

    List<ClienteVIP> buscarTodosClientesVIP();
}

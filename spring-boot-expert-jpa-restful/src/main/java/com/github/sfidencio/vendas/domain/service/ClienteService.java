package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    void salvar(ClienteRequest clienteRequest);

    void alterar(ClienteRequest clienteRequest);

    ClienteResponse buscarPorId(Integer id) throws NotFoundException;

    List<Cliente> buscarPorNome(String nome);
}

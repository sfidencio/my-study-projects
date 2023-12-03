package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequestValidationForJdbcTemplate;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    void salvarJdbcTemplate(ClienteRequestValidationForJdbcTemplate clienteRequest);


    List<Cliente> listar();

    List<Cliente> listarPorNome(String nome);

    void excluir(Integer id);

    void alterar(Cliente cliente);
}

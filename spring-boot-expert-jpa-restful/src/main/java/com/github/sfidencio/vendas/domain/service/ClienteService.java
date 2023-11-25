package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {
    void salvarJpaRepository(ClienteRequest clienteRequest);

    void salvarEntityManager(ClienteRequest clienteRequest);

    void salvarJdbcTemplate(ClienteRequest clienteRequest);

    ClienteResponse buscarPorId(Integer id) throws NotFoundException;
}

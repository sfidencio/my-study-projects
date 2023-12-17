package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.infra.integration.dto.CEP;

public interface CEPService {
    CEP buscar(String cep);
}

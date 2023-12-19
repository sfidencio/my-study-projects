package com.github.sfidencio.vendas.domain.service.imp;

import com.github.sfidencio.vendas.Application;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.infra.repository.ClienteRepository;
import com.github.sfidencio.vendas.infra.repository.ClienteVIPRespository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@DataJpaTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ClienteServiceImp.class, Application.class, ClienteVIPRespository.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteServiceImpTestNoMock {

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    @Order(1)
    void deveria_cadastrar_cliente_com_sucesso_no_mock() {
        this.clienteRepository.save(new Cliente(1, "Fulano", "79681821076", "fulano@gmail.com", null));
    }


}
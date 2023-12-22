package com.github.sfidencio.vendas.domain.service.imp;

import com.github.sfidencio.vendas.domain.entity.mongodb.ClienteVIP;
import com.github.sfidencio.vendas.domain.service.ClienteVIPService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.mongo.ClienteVIPRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceVIPImp implements ClienteVIPService {


    private final ClienteVIPRespository clienteVIPRespository;

    public ClienteServiceVIPImp(ClienteVIPRespository clienteVIPRespository) {
        this.clienteVIPRespository = clienteVIPRespository;
    }

    @Override
    public ClienteVIP buscarClienteVIP(String id) throws NotFoundException {
        return this.clienteVIPRespository.findById(id).orElseThrow(() -> new NotFoundException("Cliente VIP não encontrado"));
    }

    @Override
    public void salvarClienteVIP(ClienteVIP clienteVIP) {
        this.clienteVIPRespository.save(clienteVIP);
    }

    @Override
    public List<ClienteVIP> buscarTodosClientesVIP() {
        return this.clienteVIPRespository.findAll();
    }
}

package com.github.sfidencio.vendas.domain.service.imp;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.domain.entity.mongodb.ClienteVIP;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.ClienteRepository;
import com.github.sfidencio.vendas.infra.repository.ClienteVIPRespository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteVIPRespository clienteVIPRespository;

    public ClienteServiceImp(ClienteRepository clienteRepository, ClienteVIPRespository clienteVIPRespository) {
        this.clienteRepository = clienteRepository;
        this.clienteVIPRespository = clienteVIPRespository;
    }


    @Override
    public void salvar(ClienteRequest clienteRequest) throws NotFoundException {
        var cliente = new Cliente(null, clienteRequest.nome(), clienteRequest.cpf(), clienteRequest.email(), null);
        this.clienteRepository.save(cliente);
    }

    @Override
    public void alterar(ClienteRequest clienteRequest, Integer id) {
        var cliente = new Cliente(id, clienteRequest.nome(), clienteRequest.cpf(), clienteRequest.email(), null);
        this.clienteRepository.save(cliente);
    }

    @Override
    public List<ClienteResponse> buscarTodos() {
        return this.clienteRepository.findAll(Sort.by("nome").descending()).stream().map(c -> new ClienteResponse(c.getId(), c.getNome(), c.getCpf(), c.getEmail(), null)).toList();
    }

    @Override
    public ClienteResponse buscarClienteEPedidos(Integer id) throws NotFoundException {
        //var cliente = this.clienteRepository.findById(id).orElseThrow(()-> new NotFoundException("Cliente não encontrado"));
        var cliente = this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getPedidos());
    }

    @Override
    public void excluir(Integer id) throws NotFoundException {
        this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        this.clienteRepository.deleteById(id);
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

    /*@Override
    public List<Cliente> buscarPorNome(String nome) {
        return this.clienteRepository.findByNomeLike(nome);
    }

    @Override
    public boolean clienteExiste(String nome) {
        return this.clienteRepository.existsByNome(nome);
    }

    @Override
    public ClienteResponse buscarPorNomeApenas(String nome) throws NotFoundException {
        return this.clienteRepository.findOneByNome(nome)
                .map(c->new ClienteResponse(c.getId(),c.getNome(),c.getCpf(),c.getEmail()))
                    .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }*/
}

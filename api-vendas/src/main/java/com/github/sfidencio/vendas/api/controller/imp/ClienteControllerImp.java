package com.github.sfidencio.vendas.api.controller.imp;

import com.github.sfidencio.vendas.api.controller.ClienteController;
import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@EnableCaching
public class ClienteControllerImp implements ClienteController {

    @Autowired
    private ClienteService clienteService;


    @Override
    public ClienteResponse consultar(@PathVariable Integer id) throws NotFoundException {
        log.info("Consultando cliente por id: {}", id);
        return this.clienteService.buscarClienteEPedidos(id);
    }


    @Override
    public void salvar(@RequestBody @Valid ClienteRequest clienteRequest) throws NotFoundException {
        log.info("Salvando cliente: {}", clienteRequest);
        this.clienteService.salvar(clienteRequest);
    }

    @Override
    public void atualizar(ClienteRequest clienteRequest, Integer id) {
        log.info("Atualizando cliente: {}", clienteRequest);
        this.clienteService.alterar(clienteRequest, id);
    }


    @Override
    public void excluir(@PathVariable Integer id) throws NotFoundException {
        log.info("Excluindo cliente por id: {}", id);
        this.clienteService.excluir(id);
    }


    @Override
    public List<ClienteResponse> consultarTodos() {
        log.info("Consultando todos os clientes");
        return this.clienteService.buscarTodos();
    }


    @Override
    public ResponseEntity<MappingJacksonValue> consultarComNivelMaturidadeAplicadoNoEndPoint(@PathVariable Integer id) throws NotFoundException {
        log.info("Consultando cliente por id: {}", id);
        var clienteResponse = this.clienteService.buscarClienteEPedidos(id);
        Map<String, ClienteResponse> dados = new HashMap<>();
        dados.put("dados", clienteResponse);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteControllerImp.class).consultarComNivelMaturidadeAplicadoNoEndPoint(id));
        EntityModel entityModel = EntityModel.of(dados);
        entityModel.add(linkTo.withRel("cadastro-jpa-repository"));
        entityModel.add(linkTo.withRel("cadastro-entity-manager"));
        entityModel.add(linkTo.withRel("cadastro-jdbc-template"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);
        return ResponseEntity.ok(mappingJacksonValue);
    }

}

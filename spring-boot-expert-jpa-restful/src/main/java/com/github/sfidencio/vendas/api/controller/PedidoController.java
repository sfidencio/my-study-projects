package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.PedidoRequest;
import com.github.sfidencio.vendas.api.dto.PedidoResponse;
import com.github.sfidencio.vendas.domain.entity.StatusPedido;
import com.github.sfidencio.vendas.domain.service.PedidoService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/api/pedidos")
@Log4j2
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody List<PedidoRequest> pedidoRequest) throws NotFoundException {
        log.info("Salvando pedido");
        pedidoRequest.forEach(p -> {
            try {
                this.pedidoService.salvar(p);
            } catch (NotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    @GetMapping("/consulta-todos")
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponse> consultaTodos() {
        log.info("Consultando todos os pedidos");
        return this.pedidoService.consultaTodos();
    }

    @GetMapping("/consulta/pendentes")
    @ResponseStatus(HttpStatus.OK)
    public Set<PedidoResponse> consultaPedentes() throws NotFoundException {
        log.info("Consultando todos os pedidos pendentes");
        return this.pedidoService.obterPedidosByStatus(StatusPedido.PENDENTE);
    }

    @GetMapping("/consulta/aprovados")
    @ResponseStatus(HttpStatus.OK)
    public Set<PedidoResponse> consultaPedidosAprovados() throws NotFoundException {
        log.info("Consultando todos os pedidos aprovados");
        return this.pedidoService.obterPedidosByStatus(StatusPedido.APROVADO);
    }


    @PatchMapping("/cancela-pedido/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void cancelaPedido(@PathVariable Integer id) throws NotFoundException {
        log.info("Atualizando pedido");
        this.pedidoService.cancelar(id);
    }

    @PatchMapping("/aprova-pedido/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void aprovarPedido(@PathVariable Integer id) throws NotFoundException {
        log.info("Aprovar pedido");
        this.pedidoService.aprovar(id);
    }
}
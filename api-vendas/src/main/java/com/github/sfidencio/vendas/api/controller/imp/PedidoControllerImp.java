package com.github.sfidencio.vendas.api.controller.imp;

import com.github.sfidencio.vendas.api.controller.PedidoController;
import com.github.sfidencio.vendas.api.dto.PedidoRequest;
import com.github.sfidencio.vendas.api.dto.PedidoResponse;
import com.github.sfidencio.vendas.domain.entity.StatusPedido;
import com.github.sfidencio.vendas.domain.service.PedidoService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@Log4j2
public class PedidoControllerImp implements PedidoController {
    private final PedidoService pedidoService;

    public PedidoControllerImp(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @Override
    public void salvar(@RequestBody PedidoRequest pedidoRequest) throws NotFoundException {
        log.info("Salvando pedido");
        this.pedidoService.salvar(pedidoRequest);
    }

    @Override
    public List<PedidoResponse> consultaTodos() {
        log.info("Consultando todos os pedidos");
        return this.pedidoService.consultaTodos();
    }

    @Override
    public Set<PedidoResponse> consultaPedentes() throws NotFoundException {
        log.info("Consultando todos os pedidos pendentes");
        return this.pedidoService.obterPedidosByStatus(StatusPedido.PENDENTE);
    }


    @Override
    public Set<PedidoResponse> consultaPedidosAprovados() throws NotFoundException {
        log.info("Consultando todos os pedidos aprovados");
        return this.pedidoService.obterPedidosByStatus(StatusPedido.APROVADO);
    }


    @Override
    public void cancelaPedido(@PathVariable Integer id) throws NotFoundException {
        log.info("Atualizando pedido");
        this.pedidoService.cancelar(id);
    }

    @Override
    public void aprovarPedido(@PathVariable Integer id) throws NotFoundException {
        log.info("Aprovar pedido");
        this.pedidoService.aprovar(id);
    }
}
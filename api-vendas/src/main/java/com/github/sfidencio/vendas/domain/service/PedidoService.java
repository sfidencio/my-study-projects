package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.PedidoRequest;
import com.github.sfidencio.vendas.api.dto.PedidoResponse;
import com.github.sfidencio.vendas.domain.entity.Pedido;
import com.github.sfidencio.vendas.domain.entity.StatusPedido;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;

public interface PedidoService {
    void salvar(PedidoRequest pedidoRequest) throws NotFoundException;

    Pedido obterPedido(Integer id) throws NotFoundException;

    Set<PedidoResponse> obterPedidosByStatus(StatusPedido statusPedido) throws NotFoundException;

    List<PedidoResponse> consultaTodos();

    void cancelar(Integer id) throws NotFoundException;

    void aprovar(Integer id) throws NotFoundException;
}

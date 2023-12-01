package com.github.sfidencio.vendas.domain.service.imp;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ItemPedidoRequest;
import com.github.sfidencio.vendas.api.dto.PedidoRequest;
import com.github.sfidencio.vendas.api.dto.PedidoResponse;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.domain.entity.Pedido;
import com.github.sfidencio.vendas.domain.entity.StatusPedido;
import com.github.sfidencio.vendas.domain.service.PedidoService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.ClienteRepository;
import com.github.sfidencio.vendas.infra.repository.ItemPedidoRepository;
import com.github.sfidencio.vendas.infra.repository.PedidoRepository;
import com.github.sfidencio.vendas.infra.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PedidoServiceImp implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;


    public PedidoServiceImp(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    private void salvarItens(Pedido pedido, PedidoRequest pedidoRequest) throws NotFoundException {
        //Salvar itens
        for (ItemPedidoRequest i : pedidoRequest.itens()) {
            var item = i.toEntity();
            //Validando produto
            var produto = this.produtoRepository.findById(item.getProduto().getId()).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
            item.setValorUnitario(produto.getPreco());
            item.setProduto(produto);
            item.setPedido(pedido);
            this.itemPedidoRepository.save(item);
        }
    }

    private BigDecimal calcularTotalPedido(PedidoRequest pedidoRequest) throws NotFoundException {
        //Somar valor total do pedido usando recursos do java 8 +
        //return pedidoRequest.itens().stream()
        //        .map(i -> i.quantidade().multiply(i.produto().preco()))
        //        .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Outra forma de somar sem usar recursos do jadva 8 +
        //Aqui vamos utilizar esse modo, pois e mais simples buscarmos ou validarmos o produto, dado que api nao ta enviando o preco do produto
        //Estamos pegando dinamicamente o preco do produto
        var valorTotalPedido = BigDecimal.ZERO;
        for (ItemPedidoRequest i : pedidoRequest.itens()) {
            var produto = this.produtoRepository.findById(i.produto().id()).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
            //Setar o valor do item aqui.
            //Somando valor total do pedido
            valorTotalPedido = valorTotalPedido.add(i.quantidade().multiply(produto.getPreco()));
        }
        return valorTotalPedido;
    }

    private Cliente validarCliente(ClienteRequest clienteRequest) throws NotFoundException {
        //Validando cliente
        return this.clienteRepository.findById(clienteRequest.id()).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    @Override
    @Transactional
    public void salvar(PedidoRequest pedidoRequest) throws NotFoundException {
        log.info("Montando cabecalho pedido");
        var pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setHoraPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.PENDENTE);
        pedido.setDescricao(pedidoRequest.descricao());

        log.info("Validando cliente");
        pedido.setCliente(this.validarCliente(pedidoRequest.cliente()));

        log.info("Calculando valor total pedido");
        pedido.setValorTotal(this.calcularTotalPedido(pedidoRequest));

        log.info("Salvando itens");
        this.salvarItens(pedido, pedidoRequest);


        log.info("Salvando pedido");
        this.pedidoRepository.save(pedido);
    }

    @Override
    public Pedido obterPedido(Integer id) throws NotFoundException {
        return this.pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
    }

    @Override
    public Set<PedidoResponse> obterPedidosByStatus(StatusPedido statusPedido) throws NotFoundException {
        return this.pedidoRepository.findByStatusPedido(statusPedido).stream().map(i -> i.toResponse()).collect(Collectors.toSet());
    }


    @Override
    public List<PedidoResponse> consultaTodos() {
        return this.pedidoRepository.findAll().stream().map(i -> i.toResponse()).toList();
    }

    @Override
    public void cancelar(Integer id) throws NotFoundException {
        var pedido = this.pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        this.pedidoRepository.save(pedido);
    }

    @Override
    public void aprovar(Integer id) throws NotFoundException {
        var pedido = this.pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
        pedido.setStatusPedido(StatusPedido.APROVADO);
        this.pedidoRepository.save(pedido);
    }


}

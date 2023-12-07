package com.github.sfidencio.vendas;

import com.github.sfidencio.vendas.infra.repository.ClienteRepository;
import com.github.sfidencio.vendas.infra.repository.PedidoRepository;
import com.github.sfidencio.vendas.infra.repository.ProdutoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@Log4j2
@EnableCaching
public class Application {
    //@PersistenceContext
    //private EntityManager entityManager;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean("executarTesteDB")
    public CommandLineRunner executar(@Autowired ProdutoRepository produtoRepository,
                                      @Autowired PedidoRepository pedidoRepository,
                                      @Autowired ClienteRepository clienteRepository) {
        return args -> {
            //var cliente = new Cliente(null, "Fidencio", "12345678901");
            //this.entityManager.persist(cliente);

            //var lista = this.entityManager.createQuery("from Cliente", Cliente.class).getResultList();
            //System.out.println(lista);


            //final var clienteResponse = this.clienteService.buscarPorId(1);
            //System.out.println(clienteResponse);


            //var cliente = this.entityManager.find(Cliente.class, 1);
            //cliente.setId(1);
            //cliente.setNome("Fidencio Silva");
            //cliente.setCpf("123456789044444");
            //cliente = this.entityManager.merge(cliente);
            //System.out.println(cliente);

//
//            this.clienteService.salvar(new ClienteRequest(null, "Fulano", "45515935080", "fulano@gmail.com"));
//            this.clienteService.salvar(new ClienteRequest(null, "Beltrano", "45515935080", "fulano@gmail.com"));
//            this.clienteService.salvar(new ClienteRequest(null, "Solano", "45515935080", "fulano@gmail.com"));
//            this.clienteService.salvar(new ClienteRequest(null, "Gilson", "45515935080", "fulano@gmail.com"));
//            this.clienteService.salvar(new ClienteRequest(null, "Wilson", "45515935080", "fulano@gmail.com"));
//            var cliente = this.clienteService.buscarPorId(1);
//            System.out.println(cliente);
//            //var cliente2 = this.clienteService.buscarPorId(2);
//            //System.out.println(cliente2);
//            this.clienteService.alterar(new ClienteRequest(1, "Ciclano", "45515935080", "ciclano@gmail.com"));
//            var cliente3 = this.clienteService.buscarPorId(1);
//            System.out.println(cliente3);
//
//            //Buscar por nome
//            this.clienteService.buscarPorNome("son").forEach(System.out::println);
//
//            //Buscar por nome ou id
//            System.out.println(this.clienteService.buscarPorNomeApenas("Wilson"));
//
//            System.out.println(this.clienteService.clienteExiste("Wilson"));
//            System.out.println(this.clienteService.clienteExiste("Gerson"));


//            //Mais alguns testes
//            ClienteRequest cliente = new ClienteRequest(null, "Fulano", "45515935080", "fulano@gmail.com");
//            this.clienteService.salvar(cliente);
//            log.info("Cliente -> {}", this.clienteService.buscarPorNome("Fulano"));
//
//            //Produto
//            ProdutoRequest p1 = new ProdutoRequest(null, "Arroz Cristal", new BigDecimal("27.20"));
//            ProdutoRequest p2 = new ProdutoRequest(null, "Arroz Brejeiro", new BigDecimal("27.20"));
//            ProdutoRequest p3 = new ProdutoRequest(null, "Feijao Cristal", new BigDecimal("7.20"));
//            ProdutoRequest p4 = new ProdutoRequest(null, "Feijao Brejeiro", new BigDecimal("7.20"));
//            this.produtoService.salvar(new Produto(null, p1.descricao(), p1.preco()));
//            this.produtoService.salvar(new Produto(null, p2.descricao(), p2.preco()));
//            this.produtoService.salvar(new Produto(null, p3.descricao(), p3.preco()));
//            this.produtoService.salvar(new Produto(null, p4.descricao(), p4.preco()));
//            //log.info("Produto -> {}", this.produtoService.buscarPorDescricao("Bre"));
//            this.produtoService.buscarPorDescricao("Bre").forEach(p->log.info("Produto -> {}", p));
//
//            //Pedido Cabecalho
//            var pedido = new Pedido();
//            pedido.setId(null);
//            pedido.setDescricao("Pedido 1");
//            pedido.setDataPedido(LocalDate.now());
//            pedido.setHoraPedido(LocalDateTime.now());
//            pedido.setStatusPedido(StatusPedido.PENDENTE);
//            //var cliente = this.clienteRepository.findById(1).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
//            //System.out.println(cliente);
//            //pedido.setCliente(cliente);
//            ItemPedido it1 = new ItemPedido();
//            it1.setPedido(pedido);
//            it1.setQuantidade(new BigDecimal(10));
//
//            var p1Item = this.produtoService.obterProduto(1);
//
//            it1.setProduto(p1Item);
//            it1.setValorUnitario(new BigDecimal("27.20"));
//
//            List<ItemPedido> itens = new ArrayList<>();
//            itens.add(it1);
//            pedido.setItemPedido(itens);
//
//            pedido.setValorTotal(new BigDecimal("272.00"));
//            this.pedidoService.salvar(pedido);


        };
    }

    @Bean("executarTestRedisCache")
    public CommandLineRunner executarTestRedisCache(@Autowired RedisTemplate redisTemplate) {
        return args -> {
            //Salvando no Redis
            redisTemplate.opsForValue().set("nome", "Fidencio");
            Object valor = redisTemplate.opsForValue().get("nome");
            System.out.println(valor);
        };
    }
}

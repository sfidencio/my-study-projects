package com.github.sfidencio.dominio.servico;

import com.github.sfidencio.dominio.modelo.TipoTransacao;
import com.github.sfidencio.dominio.modelo.Transacao;
import com.github.sfidencio.dominio.servico.validacoes.SaldoInsuficiente;
import com.github.sfidencio.dominio.servico.validacoes.SaldoZerado;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteServicoTest {

    private ClienteServicoRefatorado servico;

    @BeforeEach
    void setup() {
        this.servico = new ClienteServicoRefatorado(List.of(new SaldoZerado(), new SaldoInsuficiente()));
    }

    @Test
    @DisplayName("""
                Dado que estou validando uma transacao(Venda) 
                que recebe o historico de transacoes de compra e venda de bitcoins
                Quando eu tentar realizar uma operacao de venda de 100 
                em cenario que tenho um saldo de 50
                Entao deve retornar uma excecao de negocio
            """)
    @Order(0)
    void testSaldoInsuficiente() throws ExcecaoDeNegocio {
        var todasTransacoesRealizadas = List.of(
                new Transacao(new BigDecimal("100.0"), TipoTransacao.COMPRA),
                new Transacao(new BigDecimal("100.0"), TipoTransacao.COMPRA),
                new Transacao(new BigDecimal("100.0"), TipoTransacao.VENDA),
                new Transacao(new BigDecimal("50.0"), TipoTransacao.VENDA)
        );


        Assertions.assertThrows(ExcecaoDeNegocio.class,
                () -> servico.
                        validarQuantidade(todasTransacoesRealizadas, BigDecimal.valueOf(100.0)));
    }

    @Test
    @DisplayName("""
                Dado que estou validando uma transacao(Venda) 
                que recebe o historico de transacoes de compra e venda de bitcoins
                Quando eu tentar realizar uma operacao de venda de 100 
                em cenario que tenho um saldo de 0
                Entao deve retornar uma excecao de negocio
            """)
    @Order(1)
    void testSaldoZerado() {

        List<Transacao> todasTransacoesRealizadas = List.of();

        Assertions.assertThrows(ExcecaoDeNegocio.class,
                () -> servico.
                        validarQuantidade(todasTransacoesRealizadas, BigDecimal.valueOf(100.0)));
    }

    @Test
    @DisplayName("""
                Dado que estou validando uma transacao(Venda) 
                que recebe o historico de transacoes de compra e venda de bitcoins
                Quando eu tentar realizar uma operacao de venda de 50 
                em cenario que tenho um saldo de 50
                Entao deve validar com sucesso.
            """)
    @Order(2)
    void testSaldoDisponivel() throws ExcecaoDeNegocio {

        var todasTransacoesRealizadas = List.of(
                new Transacao(new BigDecimal("100.0"), TipoTransacao.COMPRA),
                new Transacao(new BigDecimal("100.0"), TipoTransacao.COMPRA),
                new Transacao(new BigDecimal("100.0"), TipoTransacao.VENDA),
                new Transacao(new BigDecimal("50.0"), TipoTransacao.VENDA)
        );

        servico.validarQuantidade(todasTransacoesRealizadas, BigDecimal.valueOf(50.0));
    }
}
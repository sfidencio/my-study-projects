package com.github.sfidencio.aplicacao;

import com.github.sfidencio.dominio.modelo.TipoTransacao;
import com.github.sfidencio.dominio.modelo.Transacao;
import com.github.sfidencio.dominio.servico.ClienteServicoRefatorado;
import com.github.sfidencio.dominio.servico.ExcecaoDeNegocio;
import com.github.sfidencio.dominio.servico.validacoes.SaldoInsuficiente;
import com.github.sfidencio.dominio.servico.validacoes.SaldoZerado;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class AplicacaoRefatorada {
    public static Logger logger = Logger.getLogger(AplicacaoRefatorada.class.getName());

    public static void main(String[] args) {
        /*
         *  Simplificar a execução ja passando a lista de transações
         * Não vou implementar o caso de uso de compra e venda
         */

        //Cenario 1
        var todasTransacoesRealizadas = List.of(
                new Transacao(new BigDecimal("100.0"), TipoTransacao.COMPRA),
                new Transacao(new BigDecimal("100.0"), TipoTransacao.COMPRA),
                new Transacao(new BigDecimal("100.0"), TipoTransacao.VENDA),
                new Transacao(new BigDecimal("50.0"), TipoTransacao.VENDA)
        );
        var servico = new ClienteServicoRefatorado(
                List.of(new SaldoZerado(),
                        new SaldoInsuficiente()
                )
        );
        /*
         * Aqui estaria logica de compra ou venda, que chamaria a regra de negocio abaixo
         * Pressupomos que o cliente queira vender 100.00, e batendo o olho acima
         * percebemos que ele só tem 50 de saldo credor, assim ele só pode vender
         * a quantidade que não ultrapasse o saldo remanescente.
         */
        try {
            servico.validarQuantidade(todasTransacoesRealizadas, new BigDecimal("50.0"));
        } catch (ExcecaoDeNegocio ex) {
            logger.info(ex.getMessage());
        }
    }
}

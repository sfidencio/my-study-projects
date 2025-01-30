package com.github.sfidencio.dominio.servico;

import com.github.sfidencio.dominio.modelo.TipoTransacao;
import com.github.sfidencio.dominio.modelo.Transacao;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class ClienteServico {

    public static Logger logger = Logger.getLogger(ClienteServico.class.getName());

    public void validarQuantidade(final List<Transacao> transacoes, BigDecimal quantidade) throws ExcecaoDeNegocio {

        final var quantidadeCompra = this.somarQuantidade(transacoes, TipoTransacao.COMPRA);

        final var quantidadeVenda = this.somarQuantidade(transacoes, TipoTransacao.VENDA);

        final var saldo = quantidadeCompra.subtract(quantidadeVenda);

        logger.info(String.format("Saldo disponível para venda -> %s ", saldo));

        boolean comprou = quantidadeCompra.doubleValue() > 0;

        boolean temDisponivelQuantidadeParaCompra = comprou && quantidadeCompra.doubleValue() != quantidadeVenda.doubleValue();

        logger.info(String.format("Tem disponivel para compra -> %s ", temDisponivelQuantidadeParaCompra));

        if (!comprou || !temDisponivelQuantidadeParaCompra)
            throw new ExcecaoDeNegocio("Nao tem bitcoins para venda no momento....");

        if (quantidade.doubleValue() > saldo.doubleValue())
            throw new ExcecaoDeNegocio("Nao pode vender mais do que possui...");


    }

    private BigDecimal somarQuantidade(final List<Transacao> transacoes, final TipoTransacao tipoTransacao) {
        return transacoes
                .stream()
                .filter(t -> t.getTipoTransacao()
                        .equals(tipoTransacao)
                )
                .map(Transacao::getQuantidade)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}

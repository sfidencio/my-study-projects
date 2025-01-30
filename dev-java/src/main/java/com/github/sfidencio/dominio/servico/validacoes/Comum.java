package com.github.sfidencio.dominio.servico.validacoes;

import com.github.sfidencio.dominio.modelo.TipoTotalizador;
import com.github.sfidencio.dominio.modelo.TipoTransacao;
import com.github.sfidencio.dominio.modelo.Transacao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Comum {
    default BigDecimal somarQuantidade(final List<Transacao> transacoes, final TipoTransacao tipoTransacao) {
        return transacoes
                .stream()
                .filter(t -> t.getTipoTransacao()
                        .equals(tipoTransacao)
                )
                .map(Transacao::getQuantidade)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    default Map<TipoTotalizador, BigDecimal> obterTotalizadores(List<Transacao> transacoes) {
        final var somaCompra = somarQuantidade(transacoes, TipoTransacao.COMPRA);
        final var somaVenda = somarQuantidade(transacoes, TipoTransacao.VENDA);
        final var saldo = somaCompra.subtract(somaVenda);
        return Map.of(
                TipoTotalizador.T_COMPRA,
                somaCompra,
                TipoTotalizador.T_VENDA,
                somaVenda,
                TipoTotalizador.T_SALDO,
                saldo
        );
    }
}

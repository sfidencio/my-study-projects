package com.github.sfidencio.dominio.servico.validacoes;

import com.github.sfidencio.dominio.modelo.TipoTotalizador;
import com.github.sfidencio.dominio.modelo.Transacao;
import com.github.sfidencio.dominio.servico.ExcecaoDeNegocio;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class SaldoInsuficiente implements Validacao {
    public static Logger logger = Logger.getLogger(SaldoInsuficiente.class.getName());

    @Override
    public void executar(List<Transacao> transacoes, BigDecimal quantidade) throws ExcecaoDeNegocio {
        final var totalizadores = obterTotalizadores(transacoes);

        logger.info(String.format("Saldo disponível -> %.2f ", totalizadores.get(TipoTotalizador.T_SALDO)));

        if (quantidade.doubleValue() > totalizadores.get(TipoTotalizador.T_SALDO).doubleValue())
            throw new ExcecaoDeNegocio("Quantidade solicitada está acima do saldo disponível");
    }
}

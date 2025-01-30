package com.github.sfidencio.dominio.servico.validacoes;

import com.github.sfidencio.dominio.modelo.TipoTotalizador;
import com.github.sfidencio.dominio.modelo.Transacao;
import com.github.sfidencio.dominio.servico.ExcecaoDeNegocio;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class SaldoZerado implements Validacao {
    public static Logger logger = Logger.getLogger(SaldoZerado.class.getName());

    @Override
    public void executar(List<Transacao> transacoes, BigDecimal quantidade) throws ExcecaoDeNegocio {
        final var totalizadores = obterTotalizadores(transacoes);

        logger.info(String.format("Saldo disponível -> %.2f ", totalizadores.get(TipoTotalizador.T_SALDO)));

        if (totalizadores.get(TipoTotalizador.T_SALDO).doubleValue() == 0)
            throw new ExcecaoDeNegocio("Nao tem bitcoins para venda no momento....");
    }
}

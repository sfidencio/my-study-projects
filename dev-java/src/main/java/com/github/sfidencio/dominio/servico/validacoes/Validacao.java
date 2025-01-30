package com.github.sfidencio.dominio.servico.validacoes;

import com.github.sfidencio.dominio.modelo.Transacao;
import com.github.sfidencio.dominio.servico.ExcecaoDeNegocio;

import java.math.BigDecimal;
import java.util.List;

public interface Validacao extends Comum {
    void executar(final List<Transacao> transacoes, final BigDecimal quantidade) throws ExcecaoDeNegocio;
}

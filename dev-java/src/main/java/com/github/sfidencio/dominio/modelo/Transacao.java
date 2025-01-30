package com.github.sfidencio.dominio.modelo;

import java.math.BigDecimal;

public class Transacao {
    private BigDecimal quantidade;
    private TipoTransacao tipoTransacao;

    public Transacao(BigDecimal quantidade, TipoTransacao tipoTransacao) {
        this.quantidade = quantidade;
        this.tipoTransacao = tipoTransacao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }
}

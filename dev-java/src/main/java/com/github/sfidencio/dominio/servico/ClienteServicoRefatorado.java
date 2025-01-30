package com.github.sfidencio.dominio.servico;

import com.github.sfidencio.dominio.modelo.Transacao;
import com.github.sfidencio.dominio.servico.validacoes.Validacao;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class ClienteServicoRefatorado {

    public static Logger logger = Logger.getLogger(ClienteServicoRefatorado.class.getName());

    private final List<Validacao> validadores;

    /*
     * Aplicando DIP (sem framework), ou manualmente
     *
     * Open/Closed também, pois o serviço nao sabe o que será validado
     * Posso evoluir minhas validações sem que o serviço saiba disso.
     *
     * Ex: Se eu add um campo de data/hora na transacao, e resolver validar
     * Se a ultima transacao de venda for inferior a 5 dias, então não permite vender
     *
     */

    public ClienteServicoRefatorado(final List<Validacao> validadores) {
        this.validadores = validadores;
    }

    /*
     * Aplicando ->
     *           SRP - Responsabilidade Única
     *           OPEN/CLOSED - aberto para evolucao/extensao, fechado para modificacao
     */
    public void validarQuantidade(final List<Transacao> transacoes, final BigDecimal quantidade) throws ExcecaoDeNegocio {
        for (var valida : this.validadores)
            valida.executar(transacoes, quantidade);
        logger.info("Validações executadas com sucesso, fluxo segue normalmente.");
    }


}

package br.com.sfidencio.clientes.model.service;

import br.com.sfidencio.clientes.model.entity.PrestacaoServico;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PrestacaoServicoService {
    @Transactional
    PrestacaoServico salvar(PrestacaoServico prestacaoServico);

    Optional<List<PrestacaoServico>> obterTodos();

    Optional<List<PrestacaoServico>> obterPrestacaoServicosPorClienteEMes(String nome, Integer mes);
}

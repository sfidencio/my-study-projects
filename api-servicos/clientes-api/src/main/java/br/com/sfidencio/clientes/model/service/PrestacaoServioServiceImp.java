package br.com.sfidencio.clientes.model.service;

import br.com.sfidencio.clientes.model.entity.PrestacaoServico;
import br.com.sfidencio.clientes.model.repository.PrestacaoServicoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PrestacaoServioServiceImp implements PrestacaoServicoService {
    @Autowired
    private PrestacaoServicoRepository prestacaoServicoRepository;


    @Override
    @Transactional
    public PrestacaoServico salvar(PrestacaoServico prestacaoServico) {
        return this.prestacaoServicoRepository.save(prestacaoServico);
    }

    @Override
    public Optional<List<PrestacaoServico>> obterTodos() {
        return Optional.ofNullable(this.prestacaoServicoRepository.findAll());
    }

    @Override
    public Optional<List<PrestacaoServico>> obterPrestacaoServicosPorClienteEMes(String nome, Integer mes) {
        return Optional.ofNullable(this.prestacaoServicoRepository.obterPrestacaoServicosPorClienteEMes(nome, mes));
    }

}

package br.com.sfidencio.clientes.model;

import br.com.sfidencio.clientes.exceptions.BusinessException;
import br.com.sfidencio.clientes.model.entity.Cliente;
import br.com.sfidencio.clientes.model.entity.PrestacaoServico;
import br.com.sfidencio.clientes.model.entity.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServicoFacade {
    Cliente salvarCliente(Cliente cliente) throws BusinessException;

    Usuario salvarUsuario(Usuario usuario) throws BusinessException;

    Optional<List<Cliente>> obterTodosClientes();

    Optional<Cliente> obterClientePorId(UUID id);

    void excluirClientePorId(UUID id);

    PrestacaoServico salvarPrestacaoServico(PrestacaoServico prestacaoServico);

    Optional<List<PrestacaoServico>> obterTodasPrestacoesServicos();

    Optional<List<PrestacaoServico>> obterPrestacaoServicosPorClienteEMes(String nome, Integer mes);
}

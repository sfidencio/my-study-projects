package br.com.sfidencio.clientes.model.service;

import br.com.sfidencio.clientes.exceptions.BusinessException;
import br.com.sfidencio.clientes.model.entity.Cliente;
import br.com.sfidencio.clientes.model.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface UsuarioService extends UserDetailsService {
    Usuario salvar(Usuario usuario) throws BusinessException;

    Optional<List<Usuario>> obterTodos();

    Optional<Usuario> obterPorId(UUID id);

    void excluirPorId(UUID id);
}

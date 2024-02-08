package br.com.sfidencio.clientes.model.service;

import br.com.sfidencio.clientes.exceptions.BusinessException;
import br.com.sfidencio.clientes.model.entity.Usuario;
import br.com.sfidencio.clientes.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario salvar(Usuario usuario) throws BusinessException {
        //Optional<Usuario> usuarioEncontrado = this.usuarioRepository.findByLogin(usuario.getLogin());
        if (this.usuarioRepository.existsByLogin(usuario.getLogin()))
            throw new BusinessException("Usuário " + usuario.getLogin() + " já existe!");
        /*if (usuarioEncontrado.isPresent() && Objects.isNull(usuario.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe: " + usuarioEncontrado.get().getLogin() + " - " + usuarioEncontrado.get().getId());*/
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<List<Usuario>> obterTodos() {
        return Optional.ofNullable(this.usuarioRepository.findAll());
    }

    @Override
    public Optional<Usuario> obterPorId(UUID id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public void excluirPorId(UUID id) {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuarioEncontrado = this.usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        return User.builder()
                .username(usuarioEncontrado.getLogin())
                .password(usuarioEncontrado.getSenha())
                .roles("USER")
                .build();
    }
}

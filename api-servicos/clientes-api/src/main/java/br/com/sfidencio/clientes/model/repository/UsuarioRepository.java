package br.com.sfidencio.clientes.model.repository;

import br.com.sfidencio.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByLogin(@Param("login") String login);

    Boolean existsByLogin(@Param("login") String login);
}

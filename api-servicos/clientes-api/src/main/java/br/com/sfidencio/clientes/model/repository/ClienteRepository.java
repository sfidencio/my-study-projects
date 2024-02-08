package br.com.sfidencio.clientes.model.repository;

import br.com.sfidencio.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByCpf(@Param("cpf") String cpf);

    Boolean existsByCpf(@Param("cpf") String cpf);
}

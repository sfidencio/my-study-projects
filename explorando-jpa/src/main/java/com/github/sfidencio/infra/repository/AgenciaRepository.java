package com.github.sfidencio.infra.repository;

import com.github.sfidencio.domain.entity.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, UUID> {
    Optional<Agencia> findByNome(String nome);
}

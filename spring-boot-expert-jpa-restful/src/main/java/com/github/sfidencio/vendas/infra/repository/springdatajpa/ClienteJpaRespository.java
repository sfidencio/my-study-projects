package com.github.sfidencio.vendas.infra.repository.springdatajpa;

import com.github.sfidencio.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRespository extends JpaRepository<Cliente, Integer> {
}
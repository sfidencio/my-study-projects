package com.github.sfidencio.vendas.infra.repository;

import com.github.sfidencio.vendas.domain.entity.mongodb.ClienteVIP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteVIPRespository extends MongoRepository<ClienteVIP, String> {
}

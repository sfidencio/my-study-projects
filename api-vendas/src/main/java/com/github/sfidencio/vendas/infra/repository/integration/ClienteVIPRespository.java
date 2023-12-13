package com.github.sfidencio.vendas.infra.repository.integration;

import com.github.sfidencio.vendas.domain.integration.ClienteVIP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteVIPRespository extends MongoRepository<ClienteVIP, String> {
}

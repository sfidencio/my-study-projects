package com.github.sfidencio.vendas.infra.repository.jpaentitymanager;

import com.github.sfidencio.vendas.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteEntityManager {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void salvar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    public void alterar(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    public void excluir(Integer id) {
        var cliente = this.entityManager.find(Cliente.class, id);
        this.entityManager.remove(cliente);
    }

}

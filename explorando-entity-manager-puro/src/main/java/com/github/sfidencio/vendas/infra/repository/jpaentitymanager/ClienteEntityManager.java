package com.github.sfidencio.vendas.infra.repository.jpaentitymanager;

import com.github.sfidencio.vendas.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteEntityManager {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void salvar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    @Transactional
    public void alterar(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    @Transactional
    public void excluir(Integer id) {
        var cliente = this.entityManager.find(Cliente.class, id);
        this.entityManager.remove(cliente);
    }

    public Cliente buscarPorId(Integer id) {
        return this.entityManager.find(Cliente.class, id);
    }

    public List<Cliente> buscarTodos() {
        return this.entityManager.createQuery("from Cliente c", Cliente.class).getResultList();
    }

    public List<Cliente> buscarPorNome(String nome) {
        return this.entityManager.createQuery(("select c from Cliente c where c.nome like :nome"), Cliente.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

}

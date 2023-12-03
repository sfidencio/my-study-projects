package com.github.sfidencio.vendas.infra.repository;

import com.github.sfidencio.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //Query Methods ajuda a criar consultas de forma mais simples
    //Porem nao resolve todos os casos, para isso temos o @Query
    //List<Cliente> findByNomeLike(String nome);

    //List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    //Optional<Cliente> findOneByNome(String nome);

    //boolean existsByNome(String nome);

    //boolean existsById(Integer id);

    //@Query("select c from Cliente c where c.nome like :nome")
    //List<Cliente> buscaPorNome(String nome);

    //@Query(value = "select * from cliente c where c.nome like :nome", nativeQuery = true)
    //List<Cliente> buscaPorNomeNativa(String nome);

    //@Query("delete from Cliente c where c.nome = :nome")
    //@Modifying
    //void deleteByNome(String nome);


    //Obter cliente pelo id, trazendo os pedidos, mas traz o pedido caso exista
    //@Query("select c from Cliente c left join fetch c.pedidos where c.id=:id")
    //Optional<Cliente> findClienteFetchPedidos(Integer id);

    //Obter cliente pelo id, trazendo os pedidos, mas traz o pedido caso exista
    //Optional<Cliente> findByIdOrderByIdAsc(Integer integer);



}
